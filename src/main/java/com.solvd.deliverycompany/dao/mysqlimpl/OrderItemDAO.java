package com.solvd.deliverycompany.dao.mysqlimpl;

import com.solvd.deliverycompany.dao.IOrderItemDAO;
import com.solvd.deliverycompany.model.OrderItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDAO extends AbstractMySQLDAO implements IOrderItemDAO {

    private static final Logger LOGGER = LogManager.getLogger(OrderItemDAO.class);

    @Override
    public void create(OrderItem item) {
        String sql = "INSERT INTO OrderItems (item_name, quantity, price) VALUES (?, ?, ?)";

        Connection connection = getConnection();

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setString(1, item.getItemName());
            stm.setInt(2, item.getQuantity());
            stm.setDouble(3, item.getPrice());

            stm.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Error creating order item", e);
        }

        finally {
            releaseConnection(connection);
        }
    }

    @Override
    public OrderItem getById(Long id) {
        String sql = "SELECT id, item_name, quantity, price FROM OrderItems WHERE id = ?";

        Connection connection = getConnection();

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, id);

            try (ResultSet rs = stm.executeQuery()) {

                if (rs.next()) {
                    return mapResultSetToOrderItem(rs);
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting order item by id {}", id, e);
        }

        finally {
            releaseConnection(connection);
        }

        return null;
    }

    @Override
    public List<OrderItem> getAll() {
        String sql = "SELECT id, item_name, quantity, price FROM OrderItems";
        List<OrderItem> list = new ArrayList<>();

        Connection connection = getConnection();

        try (PreparedStatement stm = connection.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToOrderItem(rs));
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting all order items", e);
        }

        finally {
            releaseConnection(connection);
        }

        return list;
    }

    @Override
    public void update(OrderItem item) {
        String sql = "UPDATE OrderItems SET order_id = ?, item_name = ?, quantity = ?, price = ? WHERE id = ?";

        Connection connection = getConnection();

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(5, item.getId());
            stm.setString(1, item.getItemName());
            stm.setInt(3, item.getQuantity());
            stm.setDouble(4, item.getPrice());

            int rows = stm.executeUpdate();

            if (rows == 0) {
                LOGGER.warn("No order item found with id {}", item.getId());
            }

        } catch (SQLException e) {
            LOGGER.error("Error updating order item", e);
        }

        finally {
            releaseConnection(connection);
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM OrderItems WHERE id = ?";

        Connection connection = getConnection();

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, id);

            int rows = stm.executeUpdate();

            if (rows == 0) {
                LOGGER.warn("No order item found with id {}", id);
            }

        } catch (SQLException e) {
            LOGGER.error("Error deleting order item", e);
        }

        finally {
            releaseConnection(connection);
        }
    }

    private OrderItem mapResultSetToOrderItem(ResultSet rs) throws SQLException {
        OrderItem item = new OrderItem();

        item.setId(rs.getLong("id"));
        item.setItemName(rs.getString("item_name"));
        item.setQuantity(rs.getInt("quantity"));
        item.setPrice(rs.getDouble("price"));

        return item;
    }
}