package com.solvd.deliverycompany.dao.mysqlimpl;

import com.solvd.deliverycompany.dao.IShipmentStatusDAO;
import com.solvd.deliverycompany.model.ShipmentStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShipmentStatusDAO extends AbstractMySQLDAO implements IShipmentStatusDAO {

    private static final Logger LOGGER = LogManager.getLogger(ShipmentStatusDAO.class);

    @Override
    public void create(ShipmentStatus status) {
        String sql = "INSERT INTO shipment_statuses (status_name, description) VALUES (?, ?)";

        Connection connection = getConnection();

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setString(1, status.getStatusName());
            stm.setString(2, status.getDescription());

            stm.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Error creating shipment status", e);
        }

        finally {
            releaseConnection(connection);
        }
    }

    @Override
    public ShipmentStatus getById(Long id) {
        String sql = "SELECT id, status_name, description FROM shipment_statuses WHERE id = ?";

        Connection connection = getConnection();

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, id);

            try (ResultSet rs = stm.executeQuery()) {

                if (rs.next()) {
                    return mapResultSetToShipmentStatus(rs);
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting shipment status by id {}", id, e);
        }

        finally {
            releaseConnection(connection);
        }

        return null;
    }

    @Override
    public List<ShipmentStatus> getAll() {
        String sql = "SELECT id, status_name, description FROM shipment_statuses";

        List<ShipmentStatus> list = new ArrayList<>();

        Connection connection = getConnection();

        try (PreparedStatement stm = connection.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToShipmentStatus(rs));
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting all shipment statuses", e);
        }

        finally {
            releaseConnection(connection);
        }

        return list;
    }

    @Override
    public void update(ShipmentStatus status) {
        String sql = "UPDATE shipment_statuses SET status_name=?, description=? WHERE id=?";

        Connection connection = getConnection();

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setString(1, status.getStatusName());
            stm.setString(2, status.getDescription());
            stm.setLong(3, status.getId());

            stm.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Error updating shipment status", e);
        }

        finally {
            releaseConnection(connection);
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM shipment_statuses WHERE id = ?";

        Connection connection = getConnection();

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, id);
            stm.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Error deleting shipment status with id {}", id, e);
        }

        finally {
            releaseConnection(connection);
        }
    }

    @Override
    public ShipmentStatus getByName(String name) {
        String sql = "SELECT id, status_name, description FROM shipment_statuses WHERE status_name = ?";

        Connection connection = getConnection();

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setString(1, name);

            try (ResultSet rs = stm.executeQuery()) {

                if (rs.next()) {
                    return mapResultSetToShipmentStatus(rs);
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting shipment status by name {}", name, e);
        }

        finally {
            releaseConnection(connection);
        }

        return null;
    }

    private ShipmentStatus mapResultSetToShipmentStatus(ResultSet rs) throws SQLException {
        ShipmentStatus s = new ShipmentStatus();

        s.setId(rs.getLong("id"));
        s.setStatusName(rs.getString("status_name"));
        s.setDescription(rs.getString("description"));

        return s;
    }
}