package com.solvd.deliverycompany.dao;

import com.solvd.deliverycompany.model.Customer;

public interface ICustomerDAO extends IBaseDAO<Customer> {
    Customer getByEmail(String email);
}
