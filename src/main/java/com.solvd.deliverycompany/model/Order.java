package com.solvd.deliverycompany.model;

import java.util.List;

public class Order extends BaseEntity {
    private Customer customer;
    private Address address;
    private List<OrderItem> items;
    private String orderDate;
    private String status;
    private Double totalAmount;

    public Order() {
    }

    public Order(Long id, Customer customer, Address address, List<OrderItem> items,
                 String orderDate, String status, Double totalAmount) {
        super(id);
        this.customer = customer;
        this.address = address;
        this.items = items;
        this.orderDate = orderDate;
        this.status = status;
        this.totalAmount = totalAmount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
}