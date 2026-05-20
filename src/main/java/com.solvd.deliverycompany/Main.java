package com.solvd.deliverycompany;

import com.solvd.deliverycompany.config.ConnectionPool;
import com.solvd.deliverycompany.dao.IAddressDAO;
import com.solvd.deliverycompany.dao.ICourierDAO;
import com.solvd.deliverycompany.dao.ICustomerDAO;
import com.solvd.deliverycompany.dao.IOrderDAO;
import com.solvd.deliverycompany.dao.IOrderItemDAO;
import com.solvd.deliverycompany.jackson.JacksonParser;
import com.solvd.deliverycompany.jaxb.AddressWrapper;
import com.solvd.deliverycompany.jaxb.DeliveryWrapper;
import com.solvd.deliverycompany.jaxb.JaxbParser;
import com.solvd.deliverycompany.jaxb.JaxbWriter;
import com.solvd.deliverycompany.jaxb.OrderWrapper;
import com.solvd.deliverycompany.jaxb.UsersWrapper;
import com.solvd.deliverycompany.model.Address;
import com.solvd.deliverycompany.model.Courier;
import com.solvd.deliverycompany.model.Customer;
import com.solvd.deliverycompany.model.Delivery;
import com.solvd.deliverycompany.model.Order;
import com.solvd.deliverycompany.model.OrderItem;
import com.solvd.deliverycompany.model.User;
import com.solvd.deliverycompany.sax.AddressHandler;
import com.solvd.deliverycompany.sax.DeliveryHandler;
import com.solvd.deliverycompany.sax.OrderHandler;
import com.solvd.deliverycompany.sax.SaxParser;
import com.solvd.deliverycompany.sax.UsersHandler;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws IOException {

        try {
            ConnectionPool pool = ConnectionPool.getInstance();

            Connection connection = pool.getConnection();

            try (Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT 1")) {

                if (rs.next()) {
                    LOGGER.info("Database connection SUCCESS: {}", rs.getInt(1));
                }

            } finally {
                pool.releaseConnection(connection);
            }

        } catch (Exception e) {
            LOGGER.error("Database connection FAILED", e);
        }


        List<User> users =
                SaxParser.parse("users.xml", new UsersHandler());

        List<Address> addresses =
                SaxParser.parse("addresses.xml", new AddressHandler());

        List<Order> orders =
                SaxParser.parse("orders.xml", new OrderHandler());

        List<Delivery> deliveries =
                SaxParser.parse("deliveries.xml", new DeliveryHandler());

        LOGGER.info("Users loaded: {}", users.size());
        LOGGER.info("Addresses loaded: {}", addresses.size());
        LOGGER.info("Orders loaded: {}", orders.size());
        LOGGER.info("Deliveries loaded: {}", deliveries.size());


        UsersWrapper xmlWrapper =
                JaxbParser.unmarshal("src/main/resources/users.xml", UsersWrapper.class);

        LOGGER.info("Users loaded via JAXB: {}", xmlWrapper.getUsers().size());
        JaxbWriter.marshal(xmlWrapper, "output-users.xml");

        AddressWrapper addressWrapper =
                JaxbParser.unmarshal("src/main/resources/addresses.xml", AddressWrapper.class);

        LOGGER.info("Addresses loaded via JAXB: {}", addressWrapper.getAddresses().size());
        JaxbWriter.marshal(addressWrapper, "output-address.xml");

        OrderWrapper xmlOrderWrapper =
                JaxbParser.unmarshal("src/main/resources/orders.xml", OrderWrapper.class);

        LOGGER.info("Orders loaded via JAXB: {}", xmlOrderWrapper.getOrders().size());
        JaxbWriter.marshal(xmlOrderWrapper, "output-orders.xml");

        DeliveryWrapper deliveryWrapper =
                JaxbParser.unmarshal("src/main/resources/deliveries.xml", DeliveryWrapper.class);

        LOGGER.info("Deliveries loaded via JAXB: {}", deliveryWrapper.getDeliveries().size());
        JaxbWriter.marshal(deliveryWrapper, "output-deliveries.xml");


        UsersWrapper jsonUserWrapper =
                JacksonParser.read("users.json", UsersWrapper.class);

        LOGGER.info("Users loaded via Jackson: {}", jsonUserWrapper.getUsers().size());

        JacksonParser.write(jsonUserWrapper, "target/users_out.json");

        OrderWrapper jsonOrderWrapper =
                JacksonParser.read("orders.json", OrderWrapper.class);

        LOGGER.info("Orders loaded via Jackson: {}", jsonOrderWrapper.getOrders().size());

        JacksonParser.write(jsonOrderWrapper, "target/orders_out.json");

        SqlSessionFactory factory =
                new SqlSessionFactoryBuilder()
                        .build(Resources.getResourceAsStream("mybatis-config.xml"));

        try (SqlSession session = factory.openSession()) {

            ICustomerDAO customerDAO = session.getMapper(ICustomerDAO.class);

            List<Customer> customers = customerDAO.getAll();

            LOGGER.info("MyBatis customers: {}", customers.size());
        }

        try (SqlSession session = factory.openSession()) {

            ICourierDAO courierDAO = session.getMapper(ICourierDAO.class);

            List<Courier> couriers = courierDAO.getAll();

            LOGGER.info("MyBatis couriers: {}", couriers.size());
        }

        try (SqlSession session = factory.openSession()) {

            IOrderDAO orderDAO = session.getMapper(IOrderDAO.class);

            List<Order> order = orderDAO.getAll();

            LOGGER.info("MyBatis orders: {}", order.size());
        }

        try (SqlSession session = factory.openSession()) {

            IOrderItemDAO orderItemDAO = session.getMapper(IOrderItemDAO.class);

            List<OrderItem> items = orderItemDAO.getAll();

            LOGGER.info("MyBatis order items: {}", items.size());
        }

        try (SqlSession session = factory.openSession()) {

            IAddressDAO addressDAO = session.getMapper(IAddressDAO.class);

            List<Address> address = addressDAO.getAll();

            LOGGER.info("MyBatis addresses: {}", address.size());
        }
    }
}