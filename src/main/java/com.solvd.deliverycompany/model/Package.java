package com.solvd.deliverycompany.model;

public class Package extends BaseEntity {
    private Order order;
    private Warehouse warehouse;
    private Double weight;
    private String dimensions;
    private String trackingNumber;
    private String createdAt;

    public Package() {
    }

    public Package(Long id, Order order, Warehouse warehouse, Double weight, String dimensions,
                   String trackingNumber, String createdAt) {
        super(id);
        this.order = order;
        this.warehouse = warehouse;
        this.weight = weight;
        this.dimensions = dimensions;
        this.trackingNumber = trackingNumber;
        this.createdAt = createdAt;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}