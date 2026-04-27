package com.solvd.deliverycompany.model;

public class ShipmentStatus extends BaseEntity {
    private String statusName;
    private String description;

    public ShipmentStatus() {
    }

    public ShipmentStatus(Long id, String statusName, String description) {
        super(id);
        this.statusName = statusName;
        this.description = description;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}