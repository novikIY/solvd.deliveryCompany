package com.solvd.deliverycompany.dao.mysqlimpl;

import com.solvd.deliverycompany.dao.IOrderDAO;
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

public class OrderDAO extends AbstractMySQLDAO implements IOrderDAO {

    private static final Logger LOGGER = LogManager.getLogger(OrderDAO.class);

    @Override
    public void create(Order order) {
        String sql = "INSERT INTO Orders (customer_id, courier_id, status_id, created_at) " +
                "VALUES (?, ?, ?, ?)";

        Connection connection = getConnection();

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, order.getCustomerId());
            stm.setString(2, order.getStatus());
            stm.setString(3, order.getOrderDate());
            stm.setDouble(4, order.getTotalAmount());

            stm.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Error creating order", e);
        }

        finally {
            releaseConnection(connection);
        }
    }

    @Override
    public Order getById(Long id) {
        String sql = "SELECT id, customer_id, address_id, status, created_at, total_amount " +
                "FROM Orders WHERE id = ?";

        Connection connection = getConnection();

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, id);

            try (ResultSet rs = stm.executeQuery()) {

                if (rs.next()) {
                    return mapResultSetToOrder(rs);
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting order by id {}", id, e);
        }

        finally {
            releaseConnection(connection);
        }

        return null;
    }

    @Override
    public List<Order> getAll() {
        String sql = "SELECT id, customer_id, address_id, status, created_at, total_amount FROM Orders";

        List<Order> orders = new ArrayList<>();

        Connection connection = getConnection();

        try (PreparedStatement stm = connection.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                orders.add(mapResultSetToOrder(rs));
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting all orders", e);
        }

        finally {
            releaseConnection(connection);
        }

        return orders;
    }

    @Override
    public void update(Order order) {
        String sql = "UPDATE Orders SET customer_id = ?, courier_id = ?, status_id = ?, " +
                "created_at = ? WHERE id = ?";

        Connection connection = getConnection();

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, order.getCustomerId());
            stm.setLong(2, order.getAddressId());
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

        finally {
            releaseConnection(connection);
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM Orders WHERE id = ?";

        Connection connection = getConnection();

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, id);

            int rows = stm.executeUpdate();

            if (rows == 0) {
                LOGGER.warn("No order found with id {}", id);
            }

        } catch (SQLException e) {
            LOGGER.error("Error deleting order with id {}", id, e);
        }

        finally {
            releaseConnection(connection);
        }
    }

    @Override
    public List<Order> getByCustomerId(Long customerId) {
        String sql = "SELECT id, customer_id, address_id, status, created_at, total_amount " +
                "FROM Orders WHERE customer_id = ?";
        List<Order> orders = new ArrayList<>();

        Connection connection = getConnection();

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, customerId);

            try (ResultSet rs = stm.executeQuery()) {

                while (rs.next()) {
                    orders.add(mapResultSetToOrder(rs));
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting orders by customerId {}", customerId, e);
        }

        finally {
            releaseConnection(connection);
        }

        return orders;
    }

    @Override
    public List<Order> getByCourierId(Long courierId) {
        String sql = "SELECT id, customer_id, address_id, status, created_at, total_amount " +
                "FROM Orders WHERE courier_id = ?";
        List<Order> orders = new ArrayList<>();

        Connection connection = getConnection();

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, courierId);

            try (ResultSet rs = stm.executeQuery()) {

                while (rs.next()) {
                    orders.add(mapResultSetToOrder(rs));
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting orders by courierId {}", courierId, e);
        }

        finally {
            releaseConnection(connection);
        }

        return orders;
    }

    private Order mapResultSetToOrder(ResultSet rs) throws SQLException {
        Order o = new Order();

        o.setId(rs.getLong("id"));

        Customer customer = new Customer();
        customer.setId(rs.getLong("customer_id"));
        o.setCustomerId(customer.getId());

        Address address = new Address();
        address.setId(rs.getLong("address_id"));
        o.setAddressId(address.getId());

        o.setStatus(rs.getString("status"));
        o.setOrderDate(rs.getString("created_at"));
        o.setTotalAmount(rs.getDouble("total_amount"));

        return o;
    }
}