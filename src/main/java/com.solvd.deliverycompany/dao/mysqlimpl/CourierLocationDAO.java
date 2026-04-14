package com.solvd.deliverycompany.dao.mysqlimpl;

import com.solvd.deliverycompany.dao.ICourierLocationDAO;
import com.solvd.deliverycompany.model.Courier;
import com.solvd.deliverycompany.model.CourierLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourierLocationDAO extends AbstractMySQLDAO implements ICourierLocationDAO {

    private static final Logger LOGGER = LogManager.getLogger(CourierLocationDAO.class);

    @Override
    public void create(CourierLocation location) {
        String sql = "INSERT INTO courier_locations (courier_id, latitude, longitude, recorded_at) VALUES (?, ?, ?, ?)";

        Connection connection = getConnection();

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, location.getCourier().getId());
            stm.setDouble(2, location.getLatitude());
            stm.setDouble(3, location.getLongitude());
            stm.setString(4, location.getRecordedAt());

            stm.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Error creating courier location", e);
        }

        finally {
            releaseConnection(connection);
        }
    }

    @Override
    public CourierLocation getById(Long id) {
        String sql = "SELECT id, courier_id, latitude, longitude, recorded_at FROM courier_locations WHERE id = ?";

        Connection connection = getConnection();

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, id);

            try (ResultSet rs = stm.executeQuery()) {

                if (rs.next()) {
                    return mapResultSetToCourierLocation(rs);
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting courier location by id {}", id, e);
        }

        finally {
            releaseConnection(connection);
        }

        return null;
    }

    @Override
    public List<CourierLocation> getAll() {
        String sql = "SELECT id, courier_id, latitude, longitude, recorded_at FROM courier_locations";

        List<CourierLocation> list = new ArrayList<>();

        Connection connection = getConnection();

        try (PreparedStatement stm = connection.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToCourierLocation(rs));
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting all courier locations", e);
        }

        finally {
            releaseConnection(connection);
        }

        return list;
    }

    @Override
    public void update(CourierLocation location) {
        String sql = "UPDATE courier_locations SET courier_id=?, latitude=?, longitude=?, recorded_at=? WHERE id=?";

        Connection connection = getConnection();

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, location.getCourier().getId());
            stm.setDouble(2, location.getLatitude());
            stm.setDouble(3, location.getLongitude());
            stm.setString(4, location.getRecordedAt());
            stm.setLong(5, location.getId());

            stm.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Error updating courier location", e);
        }

        finally {
            releaseConnection(connection);
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM courier_locations WHERE id = ?";

        Connection connection = getConnection();

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, id);
            stm.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Error deleting courier location with id {}", id, e);
        }

        finally {
            releaseConnection(connection);
        }
    }

    @Override
    public List<CourierLocation> getByCourierId(Long courierId) {
        String sql = "SELECT id, courier_id, latitude, longitude, recorded_at FROM courier_locations WHERE courier_id = ?";
        List<CourierLocation> list = new ArrayList<>();

        Connection connection = getConnection();

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, courierId);

            try (ResultSet rs = stm.executeQuery()) {

                while (rs.next()) {
                    list.add(mapResultSetToCourierLocation(rs));
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting courier locations by courierId {}", courierId, e);
        }

        finally {
            releaseConnection(connection);
        }

        return list;
    }

    @Override
    public List<CourierLocation> getByDateRange(String from, String to) {

        String sql = "SELECT id, courier_id, latitude, longitude, recorded_at " +
                "FROM courier_locations WHERE recorded_at BETWEEN ? AND ?";

        List<CourierLocation> list = new ArrayList<>();

        Connection connection = getConnection();

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setString(1, from);
            stm.setString(2, to);

            try (ResultSet rs = stm.executeQuery()) {

                while (rs.next()) {
                    list.add(mapResultSetToCourierLocation(rs));
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting courier locations by date range", e);
        }

        finally {
            releaseConnection(connection);
        }

        return list;
    }

    private CourierLocation mapResultSetToCourierLocation(ResultSet rs) throws SQLException {
        CourierLocation cl = new CourierLocation();

        cl.setId(rs.getLong("id"));

        Courier c = new Courier();
        c.setId(rs.getLong("courier_id"));
        cl.setCourier(c);

        cl.setLatitude(rs.getDouble("latitude"));
        cl.setLongitude(rs.getDouble("longitude"));
        cl.setRecordedAt(rs.getString("recorded_at"));

        return cl;
    }
}