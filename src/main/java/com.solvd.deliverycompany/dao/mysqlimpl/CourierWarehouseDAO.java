package com.solvd.deliverycompany.dao.mysqlimpl;

import com.solvd.deliverycompany.dao.ICourierWarehouseDAO;
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

public class CourierWarehouseDAO extends AbstractMySQLDAO implements ICourierWarehouseDAO {

    private static final Logger LOGGER = LogManager.getLogger(CourierWarehouseDAO.class);

    @Override
    public void create(CourierWarehouse cw) {
        String sql = "INSERT INTO courier_warehouse (courier_id, warehouse_id) VALUES (?, ?)";

        Connection connection = getConnection();

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, cw.getCourier().getId());
            stm.setLong(2, cw.getWarehouse().getId());

            stm.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Error creating courier-warehouse relation", e);
        }

        finally {
            releaseConnection(connection);
        }
    }

    @Override
    public CourierWarehouse getById(Long id) {
        String sql = "SELECT id, courier_id, warehouse_id FROM courier_warehouse WHERE id = ?";

        Connection connection = getConnection();

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, id);

            try (ResultSet rs = stm.executeQuery()) {

                if (rs.next()) {
                    return mapResultSetToCourierWarehouse(rs);
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting courier-warehouse by id {}", id, e);
        }

        finally {
            releaseConnection(connection);
        }

        return null;
    }

    @Override
    public List<CourierWarehouse> getAll() {
        String sql = "SELECT id, courier_id, warehouse_id FROM courier_warehouse";

        List<CourierWarehouse> list = new ArrayList<>();

        Connection connection = getConnection();

        try (PreparedStatement stm = connection.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToCourierWarehouse(rs));
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting all courier-warehouse relations", e);
        }

        finally {
            releaseConnection(connection);
        }

        return list;
    }

    @Override
    public void update(CourierWarehouse cw) {
        String sql = "UPDATE courier_warehouse SET courier_id=?, warehouse_id=? WHERE id=?";

        Connection connection = getConnection();

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, cw.getCourier().getId());
            stm.setLong(2, cw.getWarehouse().getId());
            stm.setLong(3, cw.getId());

            stm.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Error updating courier-warehouse relation", e);
        }

        finally {
            releaseConnection(connection);
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM courier_warehouse WHERE id=?";

        Connection connection = getConnection();

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, id);
            stm.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Error deleting courier-warehouse relation", e);
        }

        finally {
            releaseConnection(connection);
        }
    }

    @Override
    public List<CourierWarehouse> getByCourierId(Long courierId) {
        String sql = "SELECT id, courier_id, warehouse_id FROM courier_warehouse WHERE courier_id = ?";
        List<CourierWarehouse> list = new ArrayList<>();

        Connection connection = getConnection();

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, courierId);

            try (ResultSet rs = stm.executeQuery()) {

                while (rs.next()) {
                    list.add(mapResultSetToCourierWarehouse(rs));
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting courier-warehouse by courierId {}", courierId, e);
        }

        finally {
            releaseConnection(connection);
        }

        return list;
    }

    @Override
    public List<CourierWarehouse> getByWarehouseId(Long warehouseId) {
        String sql = "SELECT id, courier_id, warehouse_id FROM courier_warehouse WHERE warehouse_id = ?";
        List<CourierWarehouse> list = new ArrayList<>();

        Connection connection = getConnection();

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, warehouseId);

            try (ResultSet rs = stm.executeQuery()) {

                while (rs.next()) {
                    list.add(mapResultSetToCourierWarehouse(rs));
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting courier-warehouse by warehouseId {}", warehouseId, e);
        }

        finally {
            releaseConnection(connection);
        }

        return list;
    }

    private CourierWarehouse mapResultSetToCourierWarehouse(ResultSet rs) throws SQLException {
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