package com.solvd.deliverycompany.model;

public class Warehouse extends BaseEntity {
    private String name;
    private String address;
    private String city;
    private Integer capacity;
    private String phone;
    private String createdAt;

    public Warehouse() {
    }

    public Warehouse(Long id, String name, String address, String city,
                     Integer capacity, String phone, String createdAt) {
        super(id);
        this.name = name;
        this.address = address;
        this.city = city;
        this.capacity = capacity;
        this.phone = phone;
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
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
