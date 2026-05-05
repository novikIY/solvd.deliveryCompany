package com.solvd.deliverycompany.mapper;

import com.solvd.deliverycompany.model.Address;
import java.util.List;

public interface AddressMapper {

    List<Address> getAll();

    Address getById(Long id);

    List<Address> getByCustomerId(Long customerId);

    void insert(Address address);

    void update(Address address);

    void delete(Long id);
}