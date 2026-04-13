package com.solvd.deliverycompany.dao;

import com.solvd.deliverycompany.model.Courier;
import com.solvd.deliverycompany.model.Delivery;
import com.solvd.deliverycompany.model.ShipmentStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.deliverycompany.model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeliveryDAOImpl extends AbstractDAO<Delivery> implements IDeliveryDAO {

    private static final Logger LOGGER = LogManager.getLogger(DeliveryDAOImpl.class);

    public DeliveryDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Delivery delivery) {
        String sql = "INSERT INTO Deliveries (order_id, courier_id, status_id, picked_up_at, delivered_at, created_at) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, delivery.getOrder().getId());
            stm.setLong(2, delivery.getCourier().getId());
            stm.setLong(3, delivery.getStatus().getId());
            stm.setString(4, delivery.getPickedUpAt());
            stm.setString(5, delivery.getDeliveredAt());
            stm.setString(6, delivery.getCreatedAt());

            stm.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Error creating delivery", e);
        }
    }

    @Override
    public Delivery getById(Long id) {
        String sql = "SELECT id, order_id, courier_id, status_id, picked_up_at, delivered_at, created_at FROM Deliveries WHERE id = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, id);

            try (ResultSet rs = stm.executeQuery()) {

                if (rs.next()) {
                    Delivery d = new Delivery();

                    d.setId(rs.getLong("id"));

                    Order o = new Order();
                    o.setId(rs.getLong("order_id"));
                    d.setOrder(o);

                    Courier c = new Courier();
                    c.setId(rs.getLong("courier_id"));
                    d.setCourier(c);

                    ShipmentStatus s = new ShipmentStatus();
                    s.setId(rs.getLong("status_id"));
                    d.setStatus(s);

                    d.setPickedUpAt(rs.getString("picked_up_at"));
                    d.setDeliveredAt(rs.getString("delivered_at"));
                    d.setCreatedAt(rs.getString("created_at"));

                    return d;
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting delivery by id {}", id, e);
        }

        return null;
    }

    @Override
    public List<Delivery> getAll() {
        String sql = "SELECT id, order_id, courier_id, status_id, picked_up_at, delivered_at, created_at FROM Deliveries";

        List<Delivery> list = new ArrayList<>();

        try (PreparedStatement stm = connection.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {

                Delivery d = new Delivery();

                d.setId(rs.getLong("id"));

                Order o = new Order();
                o.setId(rs.getLong("order_id"));
                d.setOrder(o);

                Courier c = new Courier();
                c.setId(rs.getLong("courier_id"));
                d.setCourier(c);

                ShipmentStatus s = new ShipmentStatus();
                s.setId(rs.getLong("status_id"));
                d.setStatus(s);

                d.setPickedUpAt(rs.getString("picked_up_at"));
                d.setDeliveredAt(rs.getString("delivered_at"));
                d.setCreatedAt(rs.getString("created_at"));

                list.add(d);
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting all deliveries", e);
        }

        return list;
    }

    @Override
    public void update(Delivery delivery) {
        String sql = "UPDATE Deliveries SET order_id = ?, courier_id = ?, status_id = ?, picked_up_at = ?, delivered_at = ?, created_at = ? WHERE id = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, delivery.getOrder().getId());
            stm.setLong(2, delivery.getCourier().getId());
            stm.setLong(3, delivery.getStatus().getId());
            stm.setString(4, delivery.getPickedUpAt());
            stm.setString(5, delivery.getDeliveredAt());
            stm.setString(6, delivery.getCreatedAt());
            stm.setLong(7, delivery.getId());

            int rows = stm.executeUpdate();

            if (rows == 0) {
                LOGGER.warn("No delivery found with id {}", delivery.getId());
            }

        } catch (SQLException e) {
            LOGGER.error("Error updating delivery", e);
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM Deliveries WHERE id = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, id);

            int rows = stm.executeUpdate();

            if (rows == 0) {
                LOGGER.warn("No delivery found to delete with id {}", id);
            }

        } catch (SQLException e) {
            LOGGER.error("Error deleting delivery", e);
        }
    }
    }