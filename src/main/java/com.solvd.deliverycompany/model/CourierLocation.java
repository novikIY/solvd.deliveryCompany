package com.solvd.deliverycompany.model;

public class CourierLocation extends BaseEntity {
    private Courier courier;
    private Double latitude;
    private Double longitude;
    private String recordedAt;

    public CourierLocation() {
    }

    public CourierLocation(Long id, Courier courier, Double latitude, Double longitude,
                           String recordedAt) {
        super(id);
        this.courier = courier;
        this.latitude = latitude;
        this.longitude = longitude;
        this.recordedAt = recordedAt;
    }

    public Courier getCourier() {
        return courier;
    }

    public void setCourier(Courier courier) {
        this.courier = courier;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getRecordedAt() {
        return recordedAt;
    }

    public void setRecordedAt(String recordedAt) {
        this.recordedAt = recordedAt;
    }
}