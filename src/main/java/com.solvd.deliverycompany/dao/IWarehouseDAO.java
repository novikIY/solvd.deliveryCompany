package com.solvd.deliverycompany.dao;

import com.solvd.deliverycompany.model.Warehouse;

import java.util.List;

public interface IWarehouseDAO extends IBaseDAO<Warehouse> {

    List<Warehouse> getByCity(String city);
    List<Warehouse> getByCapacityGreaterThan(Integer capacity);
    Warehouse getByName(String name);
}