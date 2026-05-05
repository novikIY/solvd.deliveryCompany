package com.solvd.deliverycompany.mapper;

import com.solvd.deliverycompany.model.Order;
import java.util.List;

public interface OrderMapper {

    List<Order> getAll();

    Order getById(Long id);

    List<Order> getByCustomerId(Long customerId);

    void insert(Order order);

    void update(Order order);

    void delete(Long id);
}