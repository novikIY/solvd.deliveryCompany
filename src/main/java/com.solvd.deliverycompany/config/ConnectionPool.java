package com.solvd.deliverycompany.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {

    private static ConnectionPool instance;
    private final BlockingQueue<Connection> pool;
    private static final int MAX_CONNECTIONS = 10;

    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);

    private static final String URL =
            "jdbc:mysql://localhost:3306/delivery_db";

    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    private ConnectionPool() {
        pool = new LinkedBlockingQueue<>(MAX_CONNECTIONS);

        for (int i = 0; i < MAX_CONNECTIONS; i++) {
            Connection conn = createConnection();
            if (conn != null) {
                pool.add(conn);
            } else {
                LOGGER.warn("Connection {} was not created", i);
            }
        }

        LOGGER.info("Connection pool initialized with {} connections", pool.size());
    }

    public static synchronized ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            LOGGER.info("Connection requested from pool");

            Connection connection = pool.take();

            if (connection == null || connection.isClosed()) {
                LOGGER.warn("Connection was null or closed, creating new one");
                return createConnection();
            }

            return connection;

        } catch (InterruptedException e) {
            LOGGER.error("Error getting connection from pool", e);
            throw new RuntimeException(e);
        } catch (SQLException e) {
            LOGGER.error("Connection validation failed", e);
            throw new RuntimeException(e);
        }
    }

    public void releaseConnection(Connection connection) {
        if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    pool.offer(connection);
                    LOGGER.info("Connection returned to pool");
                } else {
                    LOGGER.warn("Tried to return closed connection");
                }
            } catch (SQLException e) {
                LOGGER.error("Error checking connection state", e);
            }
        }
    }

    public int getAvailableConnections() {
        return pool.size();
    }

    private Connection createConnection() {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            LOGGER.error("Failed to create DB connection", e);
            return null;
        }
    }
}