package com.solvd.deliverycompany.dao;

import java.util.List;

public interface IBaseDAO<T> {
    void create(T entity);
    T getById(Long id);
    List<T> getAll();
    void update(T entity);
    void delete(Long id);
}
