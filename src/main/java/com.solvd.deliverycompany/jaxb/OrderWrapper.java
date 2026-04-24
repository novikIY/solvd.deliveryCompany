package com.solvd.deliverycompany.jaxb;

import com.solvd.deliverycompany.model.Order;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "orders")
public class OrderWrapper {

    private List<Order> orders;

    @XmlElement(name = "order")
    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
