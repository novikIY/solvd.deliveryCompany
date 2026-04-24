package com.solvd.deliverycompany.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "courier")
@XmlAccessorType(XmlAccessType.FIELD)

public class Courier extends User {
    private String phone;
    private String email;
    private String vehicleType;
    private String status;

    public Courier() {
    }

    public Courier(Long id, String phone, String email,
                   String vehicleType, String status) {
        this.phone = phone;
        this.email = email;
        this.vehicleType = vehicleType;
        this.status = status;
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
}