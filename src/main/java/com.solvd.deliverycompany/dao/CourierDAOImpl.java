package com.solvd.deliverycompany.dao;

import com.solvd.deliverycompany.model.Courier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourierDAOImpl extends AbstractDAO<Courier> implements ICourierDAO {

    private static final Logger LOGGER = LogManager.getLogger(CourierDAOImpl.class);

    public CourierDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Courier courier) {
        String sql = "INSERT INTO Couriers (fullName, phone, status) VALUES (?, ?, ?)";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setString(1, courier.getFullName());
            stm.setString(2, courier.getPhone());
            stm.setString(3, courier.getStatus());

            stm.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Error creating courier", e);
        }
    }

    @Override
    public Courier getById(Long id) {
        String sql = "SELECT id, fullName, phone, status FROM Couriers WHERE id = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, id);

            try (ResultSet rs = stm.executeQuery()) {

                if (rs.next()) {
                    Courier c = new Courier();

                    c.setId(rs.getLong("id"));
                    c.setFullName(rs.getString("name"));
                    c.setPhone(rs.getString("phone"));
                    c.setStatus(rs.getString("status"));

                    return c;
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting courier by id {}", id, e);
        }

        return null;
    }

    @Override
    public List<Courier> getAll() {
        String sql = "SELECT id, fullName, phone, status FROM Couriers";
        List<Courier> couriers = new ArrayList<>();

        try (PreparedStatement stm = connection.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                Courier c = new Courier();

                c.setId(rs.getLong("id"));
                c.setFullName(rs.getString("name"));
                c.setPhone(rs.getString("phone"));
                c.setStatus(rs.getString("status"));

                couriers.add(c);
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting all couriers", e);
        }

        return couriers;
    }

    @Override
    public void update(Courier courier) {
        String sql = "UPDATE Couriers SET fullName = ?, phone = ?, status = ? WHERE id = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setString(1, courier.getFullName());
            stm.setString(2, courier.getPhone());
            stm.setString(3, courier.getStatus());
            stm.setLong(4, courier.getId());

            int rows = stm.executeUpdate();

            if (rows == 0) {
                LOGGER.warn("No courier found with id {}", courier.getId());
            }

        } catch (SQLException e) {
            LOGGER.error("Error updating courier", e);
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM Couriers WHERE id = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, id);

            int rows = stm.executeUpdate();

            if (rows == 0) {
                LOGGER.warn("No courier found with id {}", id);
            }

        } catch (SQLException e) {
            LOGGER.error("Error deleting courier with id {}", id, e);
        }
    }

    @Override
    public List<Courier> getByName(String name) {
        String sql = "SELECT id, fullName, phone, status FROM Couriers WHERE fullName = ?";
        List<Courier> couriers = new ArrayList<>();

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setString(1, name);

            try (ResultSet rs = stm.executeQuery()) {

                while (rs.next()) {
                    Courier c = new Courier();

                    c.setId(rs.getLong("id"));
                    c.setFullName(rs.getString("name"));
                    c.setPhone(rs.getString("phone"));
                    c.setStatus(rs.getString("status"));

                    couriers.add(c);
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting couriers by name {}", name, e);
        }

        return couriers;
    }

    @Override
    public List<Courier> getAvailableCouriers() {
        String sql = "SELECT id, fullName, phone, status FROM Couriers WHERE status = 'AVAILABLE'";
        List<Courier> couriers = new ArrayList<>();

        try (PreparedStatement stm = connection.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                Courier c = new Courier();

                c.setId(rs.getLong("id"));
                c.setFullName(rs.getString("name"));
                c.setPhone(rs.getString("phone"));
                c.setStatus(rs.getString("status"));

                couriers.add(c);
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting available couriers", e);
        }

        return couriers;
    }
}