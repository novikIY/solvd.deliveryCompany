package com.solvd.deliverycompany.dao;

import com.solvd.deliverycompany.model.Order;
import com.solvd.deliverycompany.model.Package;
import com.solvd.deliverycompany.model.Warehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PackageDAOImpl extends AbstractDAO<Package> implements IPackageDAO {

    private static final Logger LOGGER = LogManager.getLogger(PackageDAOImpl.class);

    public PackageDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Package p) {
        String sql = "INSERT INTO packages (order_id, warehouse_id, weight, dimensions, tracking_number, created_at) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, p.getOrder().getId());
            stm.setLong(2, p.getWarehouse().getId());
            stm.setDouble(3, p.getWeight());
            stm.setString(4, p.getDimensions());
            stm.setString(5, p.getTrackingNumber());
            stm.setString(6, p.getCreatedAt());

            stm.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Error creating package", e);
        }
    }

    @Override
    public Package getById(Long id) {
        String sql = "SELECT id, order_id, warehouse_id, weight, dimensions, tracking_number, created_at " +
                "FROM packages WHERE id = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, id);

            try (ResultSet rs = stm.executeQuery()) {

                if (rs.next()) {

                    Package p = new Package();
                    p.setId(rs.getLong("id"));

                    Order o = new Order();
                    o.setId(rs.getLong("order_id"));
                    p.setOrder(o);

                    Warehouse w = new Warehouse();
                    w.setId(rs.getLong("warehouse_id"));
                    p.setWarehouse(w);

                    p.setWeight(rs.getDouble("weight"));
                    p.setDimensions(rs.getString("dimensions"));
                    p.setTrackingNumber(rs.getString("tracking_number"));
                    p.setCreatedAt(rs.getString("created_at"));

                    return p;
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting package by id {}", id, e);
        }

        return null;
    }

    @Override
    public List<Package> getAll() {
        String sql = "SELECT id, order_id, warehouse_id, weight, dimensions, tracking_number, created_at FROM packages";

        List<Package> list = new ArrayList<>();

        try (PreparedStatement stm = connection.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {

                Package p = new Package();
                p.setId(rs.getLong("id"));

                Order o = new Order();
                o.setId(rs.getLong("order_id"));
                p.setOrder(o);

                Warehouse w = new Warehouse();
                w.setId(rs.getLong("warehouse_id"));
                p.setWarehouse(w);

                p.setWeight(rs.getDouble("weight"));
                p.setDimensions(rs.getString("dimensions"));
                p.setTrackingNumber(rs.getString("tracking_number"));
                p.setCreatedAt(rs.getString("created_at"));

                list.add(p);
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting all packages", e);
        }

        return list;
    }

    @Override
    public void update(Package p) {
        String sql = "UPDATE packages SET order_id=?, warehouse_id=?, weight=?, dimensions=?, tracking_number=?, created_at=? " +
                "WHERE id=?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, p.getOrder().getId());
            stm.setLong(2, p.getWarehouse().getId());
            stm.setDouble(3, p.getWeight());
            stm.setString(4, p.getDimensions());
            stm.setString(5, p.getTrackingNumber());
            stm.setString(6, p.getCreatedAt());
            stm.setLong(7, p.getId());

            stm.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Error updating package", e);
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM packages WHERE id = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, id);
            stm.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Error deleting package with id {}", id, e);
        }
    }

    @Override
    public List<Package> getByOrderId(Long orderId) {
        String sql = "SELECT id, order_id, warehouse_id, weight, dimensions, tracking_number, created_at " +
                "FROM packages WHERE order_id = ?";

        List<Package> list = new ArrayList<>();

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, orderId);

            try (ResultSet rs = stm.executeQuery()) {

                while (rs.next()) {

                    Package p = new Package();
                    p.setId(rs.getLong("id"));

                    Order o = new Order();
                    o.setId(rs.getLong("order_id"));
                    p.setOrder(o);

                    Warehouse w = new Warehouse();
                    w.setId(rs.getLong("warehouse_id"));
                    p.setWarehouse(w);

                    p.setWeight(rs.getDouble("weight"));
                    p.setDimensions(rs.getString("dimensions"));
                    p.setTrackingNumber(rs.getString("tracking_number"));
                    p.setCreatedAt(rs.getString("created_at"));

                    list.add(p);
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting packages by orderId {}", orderId, e);
        }

        return list;
    }

    @Override
    public List<Package> getByWarehouseId(Long warehouseId) {
        String sql = "SELECT id, order_id, warehouse_id, weight, dimensions, tracking_number, created_at " +
                "FROM packages WHERE warehouse_id = ?";

        List<Package> list = new ArrayList<>();

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, warehouseId);

            try (ResultSet rs = stm.executeQuery()) {

                while (rs.next()) {

                    Package p = new Package();
                    p.setId(rs.getLong("id"));

                    Order o = new Order();
                    o.setId(rs.getLong("order_id"));
                    p.setOrder(o);

                    Warehouse w = new Warehouse();
                    w.setId(rs.getLong("warehouse_id"));
                    p.setWarehouse(w);

                    p.setWeight(rs.getDouble("weight"));
                    p.setDimensions(rs.getString("dimensions"));
                    p.setTrackingNumber(rs.getString("tracking_number"));
                    p.setCreatedAt(rs.getString("created_at"));

                    list.add(p);
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting packages by warehouseId {}", warehouseId, e);
        }

        return list;
    }
}