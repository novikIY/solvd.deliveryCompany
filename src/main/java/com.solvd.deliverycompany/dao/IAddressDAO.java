package com.solvd.deliverycompany.dao;

import com.solvd.deliverycompany.model.Address;

import java.util.List;

public interface IAddressDAO extends IBaseDAO<Address> {
    List<Address> getByCustomerId(Long customerId);
    List<Address> getByCity(String city);
    Address getByPostalCode(String postalCode);
    void deleteByCustomerId(Long customerId);
}