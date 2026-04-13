package com.solvd.deliverycompany.model;

import com.solvd.deliverycompany.model.BaseEntity;

public class OrderItem extends BaseEntity {
    private String itemName;
    private Integer quantity;
    private Double price;

    public OrderItem() {
    }

    public OrderItem(Long id, String itemName, Integer quantity, Double price) {
        super(id);
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}