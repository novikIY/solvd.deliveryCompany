package com.solvd.deliverycompany.dao;

import com.solvd.deliverycompany.model.Package;

import java.util.List;

public interface IPackageDAO extends IBaseDAO<Package> {

    List<Package> getByOrderId(Long orderId);
    List<Package> getByWarehouseId(Long warehouseId);
}