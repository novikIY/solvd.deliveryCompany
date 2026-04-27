package com.solvd.deliverycompany.model;

public class CourierWarehouse extends BaseEntity {
    private Courier courier;
    private Warehouse warehouse;

    public CourierWarehouse() {
    }

    public CourierWarehouse(Long id, Courier courier, Warehouse warehouse) {
        super(id);
        this.courier = courier;
        this.warehouse = warehouse;
    }

    public Courier getCourier() {
        return courier;
    }


    public void setCourier(Courier courier) {
        this.courier = courier;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

}