package com.solvd.deliverycompany.dao;

import com.solvd.deliverycompany.model.User;

import java.util.List;

public interface IUserDAO extends IBaseDAO<User> {
    List<User> getByFirstName(String firstName);
    List<User> getByLastName(String lastName);
    List<User> getByRole(String role);
}