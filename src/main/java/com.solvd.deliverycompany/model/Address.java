package com.solvd.deliverycompany.model;

import com.solvd.deliverycompany.model.BaseEntity;

public class Address extends BaseEntity {
    private Customer customer;
    private String street;
    private String city;
    private String postalCode;
    private String country;

    public Address() {
    }

    public Address(Long id, Customer customer, String street, String city,
                   String postalCode, String country) {
        super(id);
        this.customer = customer;
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}