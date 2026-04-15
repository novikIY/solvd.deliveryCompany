package com.solvd.deliverycompany;

import com.solvd.deliverycompany.sax.AddressHandler;
import com.solvd.deliverycompany.sax.DeliveryHandler;
import com.solvd.deliverycompany.sax.OrderHandler;
import com.solvd.deliverycompany.sax.SaxParser;
import com.solvd.deliverycompany.sax.UsersHandler;
import com.solvd.deliverycompany.model.Address;
import com.solvd.deliverycompany.model.Delivery;
import com.solvd.deliverycompany.model.Order;
import com.solvd.deliverycompany.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        List<User> users =
                SaxParser.parse("user.xml", new UsersHandler());

        List<Address> addresses =
                SaxParser.parse("address.xml", new AddressHandler());

        List<Order> orders =
                SaxParser.parse("order.xml", new OrderHandler());

        List<Delivery> deliveries =
                SaxParser.parse("delivery.xml", new DeliveryHandler());

        LOGGER.info("Users loaded: {}", users.size());
        LOGGER.info("Addresses loaded: {}", addresses.size());
        LOGGER.info("Orders loaded: {}", orders.size());
        LOGGER.info("Deliveries loaded: {}", deliveries.size());
    }
}