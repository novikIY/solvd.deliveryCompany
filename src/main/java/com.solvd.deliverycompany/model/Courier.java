package com.solvd.deliverycompany.model;

import com.solvd.deliverycompany.model.BaseEntity;

public class Courier extends BaseEntity {
    private String fullName;
    private String phone;
    private String email;
    private String vehicleType;
    private String status;
    private String createdAt;

    public Courier() {
    }

    public Courier(Long id, String fullName, String phone, String email,
                   String vehicleType, String status, String createdAt) {
        super(id);
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.vehicleType = vehicleType;
        this.status = status;
        this.createdAt = createdAt;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}