package com.solvd.deliverycompany.dao;

import com.solvd.deliverycompany.model.Payment;

import java.util.List;

public interface IPaymentDAO extends IBaseDAO<Payment> {

    List<Payment> getByOrderId(Long orderId);

}