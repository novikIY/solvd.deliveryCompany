package com.solvd.deliverycompany.dao;

import com.solvd.deliverycompany.model.Warehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WarehouseDAOImpl extends AbstractDAO<Warehouse> implements IWarehouseDAO {

    private static final Logger LOGGER = LogManager.getLogger(WarehouseDAOImpl.class);

    public WarehouseDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Warehouse warehouse) {
        String sql = "INSERT INTO Warehouses (name, address, city, capacity, phone, created_at) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setString(1, warehouse.getName());
            stm.setString(2, warehouse.getAddress());
            stm.setString(3, warehouse.getCity());
            stm.setInt(4, warehouse.getCapacity());
            stm.setString(5, warehouse.getPhone());
            stm.setString(6, warehouse.getCreatedAt());

            stm.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Error creating warehouse", e);
        }
    }

    @Override
    public Warehouse getById(Long id) {
        String sql = "SELECT id, name, address, city, capacity, phone, created_at FROM Warehouses WHERE id = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, id);

            try (ResultSet rs = stm.executeQuery()) {

                if (rs.next()) {
                    Warehouse w = new Warehouse();

                    w.setId(rs.getLong("id"));
                    w.setName(rs.getString("name"));
                    w.setAddress(rs.getString("address"));
                    w.setCity(rs.getString("city"));
                    w.setCapacity(rs.getInt("capacity"));
                    w.setPhone(rs.getString("phone"));
                    w.setCreatedAt(rs.getString("created_at"));

                    return w;
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting warehouse by id {}", id, e);
        }

        return null;
    }

    @Override
    public List<Warehouse> getAll() {
        String sql = "SELECT id, name, address, city, capacity, phone, created_at FROM Warehouses";
        List<Warehouse> list = new ArrayList<>();

        try (PreparedStatement stm = connection.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                Warehouse w = new Warehouse();

                w.setId(rs.getLong("id"));
                w.setName(rs.getString("name"));
                w.setAddress(rs.getString("address"));
                w.setCity(rs.getString("city"));
                w.setCapacity(rs.getInt("capacity"));
                w.setPhone(rs.getString("phone"));
                w.setCreatedAt(rs.getString("created_at"));

                list.add(w);
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting all warehouses", e);
        }

        return list;
    }

    @Override
    public void update(Warehouse warehouse) {
        String sql = "UPDATE Warehouses SET name = ?, address = ?, city = ?, capacity = ?, phone = ?, created_at = ? WHERE id = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setString(1, warehouse.getName());
            stm.setString(2, warehouse.getAddress());
            stm.setString(3, warehouse.getCity());
            stm.setInt(4, warehouse.getCapacity());
            stm.setString(5, warehouse.getPhone());
            stm.setString(6, warehouse.getCreatedAt());
            stm.setLong(7, warehouse.getId());

            int rows = stm.executeUpdate();

            if (rows == 0) {
                LOGGER.warn("No warehouse found with id {}", warehouse.getId());
            }

        } catch (SQLException e) {
            LOGGER.error("Error updating warehouse", e);
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM Warehouses WHERE id = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, id);

            int rows = stm.executeUpdate();

            if (rows == 0) {
                LOGGER.warn("No warehouse found to delete with id {}", id);
            }

        } catch (SQLException e) {
            LOGGER.error("Error deleting warehouse", e);
        }
    }

    @Override
    public List<Warehouse> getByCity(String city) {
        String sql = "SELECT id, name, address, city, capacity, phone, created_at FROM Warehouses WHERE city = ?";
        List<Warehouse> list = new ArrayList<>();

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setString(1, city);

            try (ResultSet rs = stm.executeQuery()) {

                while (rs.next()) {
                    Warehouse w = new Warehouse();

                    w.setId(rs.getLong("id"));
                    w.setName(rs.getString("name"));
                    w.setAddress(rs.getString("address"));
                    w.setCity(rs.getString("city"));
                    w.setCapacity(rs.getInt("capacity"));
                    w.setPhone(rs.getString("phone"));
                    w.setCreatedAt(rs.getString("created_at"));

                    list.add(w);
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting warehouses by city {}", city, e);
        }

        return list;
    }

    @Override
    public List<Warehouse> getByCapacityGreaterThan(Integer capacity) {
        String sql = "SELECT id, name, address, city, capacity, phone, created_at FROM Warehouses WHERE capacity > ?";
        List<Warehouse> list = new ArrayList<>();

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setInt(1, capacity);

            try (ResultSet rs = stm.executeQuery()) {

                while (rs.next()) {
                    Warehouse w = new Warehouse();

                    w.setId(rs.getLong("id"));
                    w.setName(rs.getString("name"));
                    w.setAddress(rs.getString("address"));
                    w.setCity(rs.getString("city"));
                    w.setCapacity(rs.getInt("capacity"));
                    w.setPhone(rs.getString("phone"));
                    w.setCreatedAt(rs.getString("created_at"));

                    list.add(w);
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting warehouses by capacity", e);
        }

        return list;
    }

    @Override
    public Warehouse getByName(String name) {
        String sql = "SELECT id, name, address, city, capacity, phone, created_at FROM Warehouses WHERE name = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setString(1, name);

            try (ResultSet rs = stm.executeQuery()) {

                if (rs.next()) {
                    Warehouse w = new Warehouse();

                    w.setId(rs.getLong("id"));
                    w.setName(rs.getString("name"));
                    w.setAddress(rs.getString("address"));
                    w.setCity(rs.getString("city"));
                    w.setCapacity(rs.getInt("capacity"));
                    w.setPhone(rs.getString("phone"));
                    w.setCreatedAt(rs.getString("created_at"));

                    return w;
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting warehouse by name {}", name, e);
        }

        return null;
    }
}