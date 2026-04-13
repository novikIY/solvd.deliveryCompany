package com.solvd.deliverycompany.dao;

import com.solvd.deliverycompany.model.CourierLocation;

import java.util.List;

public interface ICourierLocationDAO extends IBaseDAO<CourierLocation> {

    List<CourierLocation> getByCourierId(Long courierId);
    List<CourierLocation> getByDateRange(String from, String to);
}