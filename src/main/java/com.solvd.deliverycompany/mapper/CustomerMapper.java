package com.solvd.deliverycompany.mapper;

import com.solvd.deliverycompany.model.Customer;
import java.util.List;

public interface CustomerMapper {

    List<Customer> getAll();

    Customer getById(Long id);

    Customer getByEmail(String email);

    void insert(Customer customer);

    void update(Customer customer);

    void delete(Long id);

}