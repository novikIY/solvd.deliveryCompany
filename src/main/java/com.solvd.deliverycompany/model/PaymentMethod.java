package com.solvd.deliverycompany.model;

public class PaymentMethod extends BaseEntity {
    private String methodName;
    private Boolean isActive;
    private String createdAt;

    public PaymentMethod() {
    }

    public PaymentMethod(Long id, String methodName, Boolean isActive, String createdAt) {
        super(id);
        this.methodName = methodName;
        this.isActive = isActive;
        this.createdAt = createdAt;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}