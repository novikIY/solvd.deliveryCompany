package com.solvd.deliverycompany.dao;

import com.solvd.deliverycompany.model.Address;
import com.solvd.deliverycompany.model.Customer;
import com.solvd.deliverycompany.model.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl extends AbstractDAO<Order> implements IOrderDAO {

    private static final Logger LOGGER = LogManager.getLogger(OrderDAOImpl.class);

    public OrderDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Order order) {
        String sql = "INSERT INTO Orders (customer_id, courier_id, status_id, created_at) " +
                "VALUES (?, ?, ?, ?)";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, order.getCustomer().getId());
            stm.setString(2, order.getStatus());
            stm.setString(3, order.getOrderDate());
            stm.setDouble(4, order.getTotalAmount());

            stm.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Error creating order", e);
        }
    }

    @Override
    public Order getById(Long id) {
        String sql = "SELECT id, customer_id, address_id, status, created_at, total_amount " +
                "FROM Orders WHERE id = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, id);

            try (ResultSet rs = stm.executeQuery()) {

                if (rs.next()) {
                    Order o = new Order();

                    o.setId(rs.getLong("id"));

                    Customer customer = new Customer();
                    customer.setId(rs.getLong("customer_id"));
                    o.setCustomer(customer);

                    Address address = new Address();
                    address.setId(rs.getLong("address_id"));
                    o.setAddress(address);

                    o.setStatus(rs.getString("status"));
                    o.setOrderDate(rs.getString("created_at"));
                    o.setTotalAmount(rs.getDouble("total_amount"));

                    return o;
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting order by id {}", id, e);
        }

        return null;
    }

    @Override
    public List<Order> getAll() {
        String sql = "SELECT id, customer_id, address_id, status, created_at, total_amount FROM Orders";

        List<Order> orders = new ArrayList<>();

        try (PreparedStatement stm = connection.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                Order o = new Order();

                o.setId(rs.getLong("id"));

                Customer customer = new Customer();
                customer.setId(rs.getLong("customer_id"));
                o.setCustomer(customer);

                Address address = new Address();
                address.setId(rs.getLong("address_id"));
                o.setAddress(address);

                o.setStatus(rs.getString("status"));
                o.setOrderDate(rs.getString("created_at"));
                o.setTotalAmount(rs.getDouble("total_amount"));

                orders.add(o);
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting all orders", e);
        }

        return orders;
    }

    @Override
    public void update(Order order) {
        String sql = "UPDATE Orders SET customer_id = ?, courier_id = ?, status_id = ?, " +
                "created_at = ? WHERE id = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, order.getCustomer().getId());
            stm.setLong(2, order.getAddress().getId());
            stm.setString(3, order.getStatus());
            stm.setString(4, order.getOrderDate());
            stm.setDouble(5, order.getTotalAmount());
            stm.setLong(6, order.getId());

            int rows = stm.executeUpdate();

            if (rows == 0) {
                LOGGER.warn("No order found with id {}", order.getId());
            }

        } catch (SQLException e) {
            LOGGER.error("Error updating order", e);
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM Orders WHERE id = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, id);

            int rows = stm.executeUpdate();

            if (rows == 0) {
                LOGGER.warn("No order found with id {}", id);
            }

        } catch (SQLException e) {
            LOGGER.error("Error deleting order with id {}", id, e);
        }
    }

    @Override
    public List<Order> getByCustomerId(Long customerId) {
        String sql = "SELECT id, customer_id, address_id, status, created_at, total_amount " +
                "FROM Orders WHERE customer_id = ?";
        List<Order> orders = new ArrayList<>();

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, customerId);

            try (ResultSet rs = stm.executeQuery()) {

                while (rs.next()) {
                    Order o = new Order();

                    o.setId(rs.getLong("id"));

                    Customer customer = new Customer();
                    customer.setId(rs.getLong("customer_id"));
                    o.setCustomer(customer);

                    Address address = new Address();
                    address.setId(rs.getLong("address_id"));
                    o.setAddress(address);

                    o.setStatus(rs.getString("status"));
                    o.setOrderDate(rs.getString("created_at"));
                    o.setTotalAmount(rs.getDouble("total_amount"));

                    orders.add(o);
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting orders by customerId {}", customerId, e);
        }

        return orders;
    }

    @Override
    public List<Order> getByCourierId(Long courierId) {
        String sql = "SELECT id, customer_id, address_id, status, created_at, total_amount " +
                "FROM Orders WHERE courier_id = ?";
        List<Order> orders = new ArrayList<>();

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, courierId);

            try (ResultSet rs = stm.executeQuery()) {

                while (rs.next()) {
                    Order o = new Order();

                    o.setId(rs.getLong("id"));

                    Customer customer = new Customer();
                    customer.setId(rs.getLong("customer_id"));
                    o.setCustomer(customer);

                    Address address = new Address();
                    address.setId(rs.getLong("address_id"));
                    o.setAddress(address);

                    o.setStatus(rs.getString("status"));
                    o.setOrderDate(rs.getString("created_at"));
                    o.setTotalAmount(rs.getDouble("total_amount"));

                    orders.add(o);
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting orders by courierId {}", courierId, e);
        }

        return orders;
    }
}