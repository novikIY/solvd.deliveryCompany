package com.solvd.deliverycompany.model;

import com.solvd.deliverycompany.model.BaseEntity;

public class Customer extends User {
    private String email;
    private String phone;

    public Customer() {
    }

    public Customer(Long id, String email, String phone) {
        this.email = email;
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
