package com.solvd.deliverycompany.dao;

import com.solvd.deliverycompany.model.ShipmentStatus;

public interface IShipmentStatusDAO extends IBaseDAO<ShipmentStatus> {

    ShipmentStatus getByName(String name);
}