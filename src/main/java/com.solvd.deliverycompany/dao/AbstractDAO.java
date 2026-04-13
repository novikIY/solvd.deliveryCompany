package com.solvd.deliverycompany.dao;

import com.solvd.deliverycompany.dao.IBaseDAO;

import java.sql.Connection;

public abstract class AbstractDAO<T> implements IBaseDAO<T> {

    protected Connection connection;

    public AbstractDAO(Connection connection) {
        this.connection = connection;
    }
}