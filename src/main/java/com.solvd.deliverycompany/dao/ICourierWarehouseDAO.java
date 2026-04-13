package com.solvd.deliverycompany.dao;

import com.solvd.deliverycompany.model.CourierWarehouse;

import java.util.List;

public interface ICourierWarehouseDAO extends IBaseDAO<CourierWarehouse> {

    List<CourierWarehouse> getByCourierId(Long courierId);
    List<CourierWarehouse> getByWarehouseId(Long warehouseId);
}