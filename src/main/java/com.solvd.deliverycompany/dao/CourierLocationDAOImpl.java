package com.solvd.deliverycompany.dao;

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

public class CourierLocationDAOImpl extends AbstractDAO<CourierLocation> implements ICourierLocationDAO {

    private static final Logger LOGGER = LogManager.getLogger(CourierLocationDAOImpl.class);

    public CourierLocationDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    public void create(CourierLocation location) {
        String sql = "INSERT INTO courier_locations (courier_id, latitude, longitude, recorded_at) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, location.getCourier().getId());
            stm.setDouble(2, location.getLatitude());
            stm.setDouble(3, location.getLongitude());
            stm.setString(4, location.getRecordedAt());

            stm.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Error creating courier location", e);
        }
    }

    @Override
    public CourierLocation getById(Long id) {
        String sql = "SELECT id, courier_id, latitude, longitude, recorded_at FROM courier_locations WHERE id = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, id);

            try (ResultSet rs = stm.executeQuery()) {

                if (rs.next()) {
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

        } catch (SQLException e) {
            LOGGER.error("Error getting courier location by id {}", id, e);
        }

        return null;
    }

    @Override
    public List<CourierLocation> getAll() {
        String sql = "SELECT id, courier_id, latitude, longitude, recorded_at FROM courier_locations";

        List<CourierLocation> list = new ArrayList<>();

        try (PreparedStatement stm = connection.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {

                CourierLocation cl = new CourierLocation();
                cl.setId(rs.getLong("id"));

                Courier c = new Courier();
                c.setId(rs.getLong("courier_id"));
                cl.setCourier(c);

                cl.setLatitude(rs.getDouble("latitude"));
                cl.setLongitude(rs.getDouble("longitude"));
                cl.setRecordedAt(rs.getString("recorded_at"));

                list.add(cl);
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting all courier locations", e);
        }

        return list;
    }

    @Override
    public void update(CourierLocation location) {
        String sql = "UPDATE courier_locations SET courier_id=?, latitude=?, longitude=?, recorded_at=? WHERE id=?";

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
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM courier_locations WHERE id = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, id);
            stm.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Error deleting courier location with id {}", id, e);
        }
    }

    @Override
    public List<CourierLocation> getByCourierId(Long courierId) {
        String sql = "SELECT id, courier_id, latitude, longitude, recorded_at FROM courier_locations WHERE courier_id = ?";
        List<CourierLocation> list = new ArrayList<>();

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, courierId);

            try (ResultSet rs = stm.executeQuery()) {

                while (rs.next()) {

                    CourierLocation cl = new CourierLocation();
                    cl.setId(rs.getLong("id"));

                    Courier c = new Courier();
                    c.setId(rs.getLong("courier_id"));
                    cl.setCourier(c);

                    cl.setLatitude(rs.getDouble("latitude"));
                    cl.setLongitude(rs.getDouble("longitude"));
                    cl.setRecordedAt(rs.getString("recorded_at"));

                    list.add(cl);
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting courier locations by courierId {}", courierId, e);
        }

        return list;
    }

    @Override
    public List<CourierLocation> getByDateRange(String from, String to) {

        String sql = "SELECT id, courier_id, latitude, longitude, recorded_at " +
                "FROM courier_locations WHERE recorded_at BETWEEN ? AND ?";

        List<CourierLocation> list = new ArrayList<>();

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setString(1, from);
            stm.setString(2, to);

            try (ResultSet rs = stm.executeQuery()) {

                while (rs.next()) {

                    CourierLocation cl = new CourierLocation();
                    cl.setId(rs.getLong("id"));

                    Courier c = new Courier();
                    c.setId(rs.getLong("courier_id"));
                    cl.setCourier(c);

                    cl.setLatitude(rs.getDouble("latitude"));
                    cl.setLongitude(rs.getDouble("longitude"));
                    cl.setRecordedAt(rs.getString("recorded_at"));

                    list.add(cl);
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting courier locations by date range", e);
        }

        return list;
    }
}