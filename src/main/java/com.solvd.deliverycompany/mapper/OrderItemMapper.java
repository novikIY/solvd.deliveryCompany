package com.solvd.deliverycompany.mapper;

import com.solvd.deliverycompany.model.OrderItem;
import java.util.List;

public interface OrderItemMapper {

    List<OrderItem> getAll();

    OrderItem getById(Long id);

    List<OrderItem> getByOrderId(Long orderId);

    void insert(OrderItem item);

    void update(OrderItem item);

    void delete(Long id);
}