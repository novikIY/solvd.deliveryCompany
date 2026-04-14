package com.solvd.deliverycompany.dao.mysqlimpl;

import com.solvd.deliverycompany.dao.ICourierDAO;
import com.solvd.deliverycompany.model.Courier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourierDAO extends AbstractMySQLDAO implements ICourierDAO {

    private static final Logger LOGGER = LogManager.getLogger(CourierDAO.class);

    @Override
    public void create(Courier courier) {
        String sql = "INSERT INTO Couriers (phone, status) VALUES (?, ?)";

        Connection connection = getConnection();

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setString(1, courier.getPhone());
            stm.setString(2, courier.getStatus());

            stm.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Error creating courier", e);
        }

        finally {
            releaseConnection(connection);
        }
    }

    @Override
    public Courier getById(Long id) {
        String sql = "SELECT id, phone, status FROM Couriers WHERE id = ?";

        Connection connection = getConnection();

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, id);

            try (ResultSet rs = stm.executeQuery()) {

                if (rs.next()) {
                    return mapResultSetToCourier(rs);
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting courier by id {}", id, e);
        }

        finally {
            releaseConnection(connection);
        }

        return null;
    }

    @Override
    public List<Courier> getAll() {
        String sql = "SELECT id, phone, status FROM Couriers";
        List<Courier> couriers = new ArrayList<>();

        Connection connection = getConnection();

        try (PreparedStatement stm = connection.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                couriers.add(mapResultSetToCourier(rs));;
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting all couriers", e);
        }

        finally {
            releaseConnection(connection);
        }

        return couriers;
    }

    @Override
    public void update(Courier courier) {
        String sql = "UPDATE Couriers SET phone = ?, status = ? WHERE id = ?";

        Connection connection = getConnection();

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setString(1, courier.getPhone());
            stm.setString(2, courier.getStatus());
            stm.setLong(3, courier.getId());

            int rows = stm.executeUpdate();

            if (rows == 0) {
                LOGGER.warn("No courier found with id {}", courier.getId());
            }

        } catch (SQLException e) {
            LOGGER.error("Error updating courier", e);
        }

        finally {
            releaseConnection(connection);
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM Couriers WHERE id = ?";

        Connection connection = getConnection();

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, id);

            int rows = stm.executeUpdate();

            if (rows == 0) {
                LOGGER.warn("No courier found with id {}", id);
            }

        } catch (SQLException e) {
            LOGGER.error("Error deleting courier with id {}", id, e);
        }

        finally {
            releaseConnection(connection);
        }
    }

    @Override
    public List<Courier> getAvailableCouriers() {
        String sql = "SELECT id, phone, status FROM Couriers WHERE status = 'AVAILABLE'";
        List<Courier> couriers = new ArrayList<>();

        Connection connection = getConnection();

        try (PreparedStatement stm = connection.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                couriers.add(mapResultSetToCourier(rs));;
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting available couriers", e);
        }

        finally {
            releaseConnection(connection);
        }

        return couriers;
    }

    private Courier mapResultSetToCourier(ResultSet rs) throws SQLException {
        Courier c = new Courier();

        c.setId(rs.getLong("id"));
        c.setPhone(rs.getString("phone"));
        c.setStatus(rs.getString("status"));

        return c;
    }
}