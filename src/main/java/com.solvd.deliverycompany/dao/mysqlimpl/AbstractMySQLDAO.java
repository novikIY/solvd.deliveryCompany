package com.solvd.deliverycompany.dao.mysqlimpl;

import com.solvd.deliverycompany.config.ConnectionPool;

import java.sql.Connection;

public abstract class AbstractMySQLDAO {

    protected Connection getConnection() {
        return ConnectionPool.getInstance().getConnection();
    }

    protected void releaseConnection(Connection connection) {
        if (connection != null) {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }
}