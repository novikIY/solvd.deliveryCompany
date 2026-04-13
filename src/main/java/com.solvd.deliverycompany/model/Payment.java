package com.solvd.deliverycompany.model;

import com.solvd.deliverycompany.model.BaseEntity;
import com.solvd.deliverycompany.model.Order;
import com.solvd.deliverycompany.model.PaymentMethod;

public class Payment extends BaseEntity {
    private Order order;
    private PaymentMethod paymentMethod;
    private Double amount;
    private String status;
    private String paidAt;
    private String createdAt;

    public Payment() {
    }

    public Payment(Long id, Order order, PaymentMethod paymentMethod,
                   Double amount, String status, String paidAt, String createdAt) {
        super(id);
        this.order = order;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.status = status;
        this.paidAt = paidAt;
        this.createdAt = createdAt;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(String paidAt) {
        this.paidAt = paidAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}