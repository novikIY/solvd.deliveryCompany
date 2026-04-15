package com.solvd.deliverycompany.sax;

import com.solvd.deliverycompany.model.Order;
import com.solvd.deliverycompany.model.OrderItem;
import org.xml.sax.Attributes;

import java.util.ArrayList;
import java.util.List;

public class OrderHandler extends BaseHandler<List<Order>> {

    private List<Order> orders = new ArrayList<>();
    private Order currentOrder;
    private OrderItem currentItem;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {

        if ("order".equals(qName)) {
            currentOrder = new Order();
            currentOrder.setItems(new ArrayList<>());
        }

        if ("item".equals(qName)) {
            currentItem = new OrderItem();
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
                if (currentItem != null) {
                    currentItem.setId(parseLongSafe(value));
                } else if (currentOrder != null) {
                    currentOrder.setId(parseLongSafe(value));
                }
                break;

            case "itemName":
                if (currentItem != null) {
                    currentItem.setItemName(value);
                }
                break;

            case "quantity":
                if (currentItem != null) {
                    currentItem.setQuantity(parseIntSafe(value));
                }
                break;

            case "price":
                if (currentItem != null) {
                    currentItem.setPrice(parseDoubleSafe(value));
                }
                break;

            case "item":
                if (currentOrder != null && currentItem != null) {
                    currentOrder.getItems().add(currentItem);
                    currentItem = null;
                }
                break;

            case "status":
                if (currentOrder != null) {
                    currentOrder.setStatus(value);
                }
                break;

            case "orderDate":
                if (currentOrder != null) {
                    currentOrder.setOrderDate(value);
                }
                break;

            case "totalAmount":
                if (currentOrder != null) {
                    currentOrder.setTotalAmount(parseDoubleSafe(value));
                }
                break;

            case "order":
                if (currentOrder != null) {
                    orders.add(currentOrder);
                    currentOrder = null;
                }
                break;
        }
    }

    @Override
    public List<Order> getResult() {
        return orders;
    }
}