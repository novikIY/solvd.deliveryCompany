package com.solvd.deliverycompany.model;

import com.solvd.deliverycompany.model.BaseEntity;

public class Customer extends BaseEntity {
    private String name;
    private String email;
    private String phone;
    private String createdAt;

    public Customer() {
    }

    public Customer(Long id, String name, String email, String phone, String createdAt) {
        super(id);
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
