package com.solvd.deliverycompany.sax;

import com.solvd.deliverycompany.model.Courier;
import com.solvd.deliverycompany.model.Delivery;
import com.solvd.deliverycompany.model.Order;
import com.solvd.deliverycompany.model.ShipmentStatus;
import org.xml.sax.Attributes;

import java.util.ArrayList;
import java.util.List;

public class DeliveryHandler extends BaseHandler<List<Delivery>> {

    private List<Delivery> deliveries = new ArrayList<>();
    private Delivery currentDelivery;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {

        if ("delivery".equals(qName)) {
            currentDelivery = new Delivery();
        }

        content.setLength(0);
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        content.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) {

        String value = getValue();

        switch (qName) {

            case "id":
                if (currentDelivery != null) {
                    currentDelivery.setId(parseLongSafe(value));
                }
                break;

            case "orderId":
                if (currentDelivery != null) {
                    Order order = new Order();
                    order.setId(parseLongSafe(value));
                    currentDelivery.setOrder(order);
                }
                break;

            case "courierId":
                if (currentDelivery != null) {
                    Courier courier = new Courier();
                    courier.setId(parseLongSafe(value));
                    currentDelivery.setCourier(courier);
                }
                break;

            case "statusId":
                if (currentDelivery != null) {
                    ShipmentStatus status = new ShipmentStatus();
                    status.setId(parseLongSafe(value));
                    currentDelivery.setStatus(status);
                }
                break;

            case "pickedUpAt":
                if (currentDelivery != null) {
                    currentDelivery.setPickedUpAt(value);
                }
                break;

            case "deliveredAt":
                if (currentDelivery != null) {
                    currentDelivery.setDeliveredAt(value);
                }
                break;

            case "createdAt":
                if (currentDelivery != null) {
                    currentDelivery.setCreatedAt(value);
                }
                break;

            case "delivery":
                if (currentDelivery != null) {
                    deliveries.add(currentDelivery);
                    currentDelivery = null;
                }
                break;
        }
    }

    @Override
    public List<Delivery> getResult() {
        return deliveries;
    }
}