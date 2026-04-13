package com.solvd.deliverycompany.dao;

import com.solvd.deliverycompany.model.OrderItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDAOImpl extends AbstractDAO<OrderItem> implements IOrderItemDAO {

    private static final Logger LOGGER = LogManager.getLogger(OrderItemDAOImpl.class);

    public OrderItemDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    public void create(OrderItem item) {
        String sql = "INSERT INTO OrderItems (item_name, quantity, price) VALUES (?, ?, ?)";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setString(1, item.getItemName());
            stm.setInt(2, item.getQuantity());
            stm.setDouble(3, item.getPrice());

            stm.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Error creating order item", e);
        }
    }

    @Override
    public OrderItem getById(Long id) {
        String sql = "SELECT id, item_name, quantity, price FROM OrderItems WHERE id = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, id);

            try (ResultSet rs = stm.executeQuery()) {

                if (rs.next()) {
                    OrderItem item = new OrderItem();

                    item.setId(rs.getLong("id"));
                    item.setItemName(rs.getString("item_name"));
                    item.setQuantity(rs.getInt("quantity"));
                    item.setPrice(rs.getDouble("price"));

                    return item;
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting order item by id {}", id, e);
        }

        return null;
    }

    @Override
    public List<OrderItem> getAll() {
        String sql = "SELECT id, item_name, quantity, price FROM OrderItems";
        List<OrderItem> list = new ArrayList<>();

        try (PreparedStatement stm = connection.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                OrderItem item = new OrderItem();

                item.setId(rs.getLong("id"));
                item.setItemName(rs.getString("item_name"));
                item.setQuantity(rs.getInt("quantity"));
                item.setPrice(rs.getDouble("price"));

                list.add(item);
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting all order items", e);
        }

        return list;
    }

    @Override
    public void update(OrderItem item) {
        String sql = "UPDATE OrderItems SET order_id = ?, item_name = ?, quantity = ?, price = ? WHERE id = ?";

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
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM OrderItems WHERE id = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, id);

            int rows = stm.executeUpdate();

            if (rows == 0) {
                LOGGER.warn("No order item found with id {}", id);
            }

        } catch (SQLException e) {
            LOGGER.error("Error deleting order item", e);
        }
    }
}