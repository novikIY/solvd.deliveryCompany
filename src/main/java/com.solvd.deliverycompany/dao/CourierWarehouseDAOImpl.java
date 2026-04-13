package com.solvd.deliverycompany.dao;

import com.solvd.deliverycompany.model.Courier;
import com.solvd.deliverycompany.model.CourierWarehouse;
import com.solvd.deliverycompany.model.Warehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourierWarehouseDAOImpl extends AbstractDAO<CourierWarehouse> implements ICourierWarehouseDAO {

    private static final Logger LOGGER = LogManager.getLogger(CourierWarehouseDAOImpl.class);

    public CourierWarehouseDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    public void create(CourierWarehouse cw) {
        String sql = "INSERT INTO courier_warehouse (courier_id, warehouse_id) VALUES (?, ?)";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, cw.getCourier().getId());
            stm.setLong(2, cw.getWarehouse().getId());

            stm.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Error creating courier-warehouse relation", e);
        }
    }

    @Override
    public CourierWarehouse getById(Long id) {
        String sql = "SELECT id, courier_id, warehouse_id FROM courier_warehouse WHERE id = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, id);

            try (ResultSet rs = stm.executeQuery()) {

                if (rs.next()) {
                    CourierWarehouse cw = new CourierWarehouse();
                    cw.setId(rs.getLong("id"));

                    Courier c = new Courier();
                    c.setId(rs.getLong("courier_id"));
                    cw.setCourier(c);

                    Warehouse w = new Warehouse();
                    w.setId(rs.getLong("warehouse_id"));
                    cw.setWarehouse(w);

                    return cw;
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting courier-warehouse by id {}", id, e);
        }

        return null;
    }

    @Override
    public List<CourierWarehouse> getAll() {
        String sql = "SELECT id, courier_id, warehouse_id FROM courier_warehouse";

        List<CourierWarehouse> list = new ArrayList<>();

        try (PreparedStatement stm = connection.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {

                CourierWarehouse cw = new CourierWarehouse();
                cw.setId(rs.getLong("id"));

                Courier c = new Courier();
                c.setId(rs.getLong("courier_id"));
                cw.setCourier(c);

                Warehouse w = new Warehouse();
                w.setId(rs.getLong("warehouse_id"));
                cw.setWarehouse(w);

                list.add(cw);
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting all courier-warehouse relations", e);
        }

        return list;
    }

    @Override
    public void update(CourierWarehouse cw) {
        String sql = "UPDATE courier_warehouse SET courier_id=?, warehouse_id=? WHERE id=?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, cw.getCourier().getId());
            stm.setLong(2, cw.getWarehouse().getId());
            stm.setLong(3, cw.getId());

            stm.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Error updating courier-warehouse relation", e);
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM courier_warehouse WHERE id=?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, id);
            stm.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Error deleting courier-warehouse relation", e);
        }
    }

    @Override
    public List<CourierWarehouse> getByCourierId(Long courierId) {
        String sql = "SELECT id, courier_id, warehouse_id FROM courier_warehouse WHERE courier_id = ?";
        List<CourierWarehouse> list = new ArrayList<>();

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, courierId);

            try (ResultSet rs = stm.executeQuery()) {

                while (rs.next()) {

                    CourierWarehouse cw = new CourierWarehouse();
                    cw.setId(rs.getLong("id"));

                    Courier c = new Courier();
                    c.setId(rs.getLong("courier_id"));
                    cw.setCourier(c);

                    Warehouse w = new Warehouse();
                    w.setId(rs.getLong("warehouse_id"));
                    cw.setWarehouse(w);

                    list.add(cw);
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting courier-warehouse by courierId {}", courierId, e);
        }

        return list;
    }

    @Override
    public List<CourierWarehouse> getByWarehouseId(Long warehouseId) {
        String sql = "SELECT id, courier_id, warehouse_id FROM courier_warehouse WHERE warehouse_id = ?";
        List<CourierWarehouse> list = new ArrayList<>();

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, warehouseId);

            try (ResultSet rs = stm.executeQuery()) {

                while (rs.next()) {

                    CourierWarehouse cw = new CourierWarehouse();
                    cw.setId(rs.getLong("id"));

                    Courier c = new Courier();
                    c.setId(rs.getLong("courier_id"));
                    cw.setCourier(c);

                    Warehouse w = new Warehouse();
                    w.setId(rs.getLong("warehouse_id"));
                    cw.setWarehouse(w);

                    list.add(cw);
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting courier-warehouse by warehouseId {}", warehouseId, e);
        }

        return list;
    }
}