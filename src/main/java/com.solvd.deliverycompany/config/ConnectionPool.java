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

    private static final String URL = "jdbc:mysql://localhost:3306/delivery_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "password";

    private ConnectionPool() {
        pool = new LinkedBlockingQueue<>(MAX_CONNECTIONS);

        for (int i = 0; i < MAX_CONNECTIONS; i++) {
            pool.add(createConnection());
        }

        LOGGER.info("Connection pool initialized with {} connections", MAX_CONNECTIONS);
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
            return pool.take();
        } catch (InterruptedException e) {
            throw new RuntimeException("Error getting connection from pool", e);
        }
    }

    public void releaseConnection(Connection connection) {
        if (connection != null) {
            pool.offer(connection);
            LOGGER.info("Connection returned to pool");
        }
    }

    public int getAvailableConnections() {
        return pool.size();
    }

    private Connection createConnection() {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create DB connection", e);
        }
    }
}