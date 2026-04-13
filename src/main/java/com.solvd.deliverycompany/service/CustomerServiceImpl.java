package com.solvd.deliverycompany.service;

import com.solvd.deliverycompany.dao.ICustomerDAO;
import com.solvd.deliverycompany.model.Customer;

import java.util.List;

public class CustomerServiceImpl implements ICustomerService {

    private final ICustomerDAO customerDAO;

    public CustomerServiceImpl(ICustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @Override
    public void createCustomer(Customer customer) {
        customerDAO.create(customer);
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerDAO.getById(id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerDAO.getAll();
    }

    @Override
    public void updateCustomer(Customer customer) {
        customerDAO.update(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        customerDAO.delete(id);
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        return customerDAO.getByEmail(email);
    }
}