package com.solvd.deliverycompany.jaxb;

import com.solvd.deliverycompany.model.Delivery;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "deliveries")
public class DeliveryWrapper {

    private List<Delivery> deliveries;

    @XmlElement(name = "delivery")
    public List<Delivery> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<Delivery> deliveries) {
        this.deliveries = deliveries;
    }
}