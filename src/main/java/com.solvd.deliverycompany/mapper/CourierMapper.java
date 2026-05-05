package com.solvd.deliverycompany.mapper;

import com.solvd.deliverycompany.model.Courier;
import java.util.List;

public interface CourierMapper {

    List<Courier> getAll();

    Courier getById(Long id);

    void insert(Courier courier);

    void update(Courier courier);

    void delete(Long id);
}