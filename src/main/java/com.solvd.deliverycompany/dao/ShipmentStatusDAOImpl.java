package com.solvd.deliverycompany.dao;

import com.solvd.deliverycompany.model.ShipmentStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShipmentStatusDAOImpl extends AbstractDAO<ShipmentStatus> implements IShipmentStatusDAO {

    private static final Logger LOGGER = LogManager.getLogger(ShipmentStatusDAOImpl.class);

    public ShipmentStatusDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    public void create(ShipmentStatus status) {
        String sql = "INSERT INTO shipment_statuses (status_name, description) VALUES (?, ?)";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setString(1, status.getStatusName());
            stm.setString(2, status.getDescription());

            stm.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Error creating shipment status", e);
        }
    }

    @Override
    public ShipmentStatus getById(Long id) {
        String sql = "SELECT id, status_name, description FROM shipment_statuses WHERE id = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, id);

            try (ResultSet rs = stm.executeQuery()) {

                if (rs.next()) {

                    ShipmentStatus s = new ShipmentStatus();
                    s.setId(rs.getLong("id"));
                    s.setStatusName(rs.getString("status_name"));
                    s.setDescription(rs.getString("description"));

                    return s;
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting shipment status by id {}", id, e);
        }

        return null;
    }

    @Override
    public List<ShipmentStatus> getAll() {
        String sql = "SELECT id, status_name, description FROM shipment_statuses";

        List<ShipmentStatus> list = new ArrayList<>();

        try (PreparedStatement stm = connection.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {

                ShipmentStatus s = new ShipmentStatus();
                s.setId(rs.getLong("id"));
                s.setStatusName(rs.getString("status_name"));
                s.setDescription(rs.getString("description"));

                list.add(s);
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting all shipment statuses", e);
        }

        return list;
    }

    @Override
    public void update(ShipmentStatus status) {
        String sql = "UPDATE shipment_statuses SET status_name=?, description=? WHERE id=?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setString(1, status.getStatusName());
            stm.setString(2, status.getDescription());
            stm.setLong(3, status.getId());

            stm.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Error updating shipment status", e);
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM shipment_statuses WHERE id = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, id);
            stm.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Error deleting shipment status with id {}", id, e);
        }
    }

    @Override
    public ShipmentStatus getByName(String name) {
        String sql = "SELECT id, status_name, description FROM shipment_statuses WHERE status_name = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setString(1, name);

            try (ResultSet rs = stm.executeQuery()) {

                if (rs.next()) {

                    ShipmentStatus s = new ShipmentStatus();
                    s.setId(rs.getLong("id"));
                    s.setStatusName(rs.getString("status_name"));
                    s.setDescription(rs.getString("description"));

                    return s;
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting shipment status by name {}", name, e);
        }

        return null;
    }
}