package com.solvd.deliverycompany.service;

import com.solvd.deliverycompany.model.Customer;

import java.util.List;

public interface ICustomerService {

    void createCustomer(Customer customer);

    Customer getCustomerById(Long id);

    List<Customer> getAllCustomers();

    void updateCustomer(Customer customer);

    void deleteCustomer(Long id);

    Customer getCustomerByEmail(String email);
}