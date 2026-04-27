package com.solvd.deliverycompany.model;

public class Delivery extends BaseEntity {
    private Order order;
    private Courier courier;
    private ShipmentStatus status;
    private String pickedUpAt;
    private String deliveredAt;
    private String createdAt;

    public Delivery() {
    }

    public Delivery(Long id, Order order, Courier courier, ShipmentStatus status,
                    String pickedUpAt, String deliveredAt, String createdAt) {
        super(id);
        this.order = order;
        this.courier = courier;
        this.status = status;
        this.pickedUpAt = pickedUpAt;
        this.deliveredAt = deliveredAt;
        this.createdAt = createdAt;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Courier getCourier() {
        return courier;
    }

    public void setCourier(Courier courier) {
        this.courier = courier;
    }

    public ShipmentStatus getStatus() {
        return status;
    }

    public void setStatus(ShipmentStatus status) {
        this.status = status;
    }

    public String getPickedUpAt() {
        return pickedUpAt;
    }

    public void setPickedUpAt(String pickedUpAt) {
        this.pickedUpAt = pickedUpAt;
    }

    public String getDeliveredAt() {
        return deliveredAt;
    }

    public void setDeliveredAt(String deliveredAt) {
        this.deliveredAt = deliveredAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}