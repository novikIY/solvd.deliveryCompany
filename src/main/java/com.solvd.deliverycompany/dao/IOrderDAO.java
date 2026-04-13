package com.solvd.deliverycompany.dao;
import com.solvd.deliverycompany.model.Order;
import java.util.List;

public interface IOrderDAO extends IBaseDAO<Order> {
    List<Order> getByCustomerId(Long customerId);
    List<Order> getByCourierId(Long courierId);
}

