package com.solvd.deliverycompany.dao.mysqlimpl;

import com.solvd.deliverycompany.dao.IPaymentMethodDAO;
import com.solvd.deliverycompany.model.PaymentMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentMethodDAO extends AbstractMySQLDAO implements IPaymentMethodDAO {

    private static final Logger LOGGER = LogManager.getLogger(PaymentMethodDAO.class);

    @Override
    public void create(PaymentMethod pm) {
        String sql = "INSERT INTO PaymentMethods (method_name, is_active, created_at) VALUES (?, ?, ?)";

        Connection connection = getConnection();

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setString(1, pm.getMethodName());
            stm.setBoolean(2, pm.isActive());
            stm.setString(3, pm.getCreatedAt());

            stm.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Error creating payment method", e);
        }

        finally {
            releaseConnection(connection);
        }
    }

    @Override
    public PaymentMethod getById(Long id) {
        String sql = "SELECT id, method_name, is_active, created_at FROM PaymentMethods WHERE id = ?";

        Connection connection = getConnection();

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, id);

            try (ResultSet rs = stm.executeQuery()) {

                if (rs.next()) {
                    return mapResultSetToPaymentMethod(rs);
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting payment method by id {}", id, e);
        }

        finally {
            releaseConnection(connection);
        }

        return null;
    }

    @Override
    public List<PaymentMethod> getAll() {
        String sql = "SELECT id, method_name, is_active, created_at FROM PaymentMethods";
        List<PaymentMethod> list = new ArrayList<>();

        Connection connection = getConnection();

        try (PreparedStatement stm = connection.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToPaymentMethod(rs));
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting all payment methods", e);
        }

        finally {
            releaseConnection(connection);
        }

        return list;
    }

    @Override
    public void update(PaymentMethod pm) {
        String sql = "UPDATE PaymentMethods SET method_name = ?, is_active = ?, created_at = ? WHERE id = ?";

        Connection connection = getConnection();

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setString(1, pm.getMethodName());
            stm.setBoolean(2, pm.isActive());
            stm.setString(3, pm.getCreatedAt());
            stm.setLong(4, pm.getId());

            int rows = stm.executeUpdate();

            if (rows == 0) {
                LOGGER.warn("No payment method found with id {}", pm.getId());
            }

        } catch (SQLException e) {
            LOGGER.error("Error updating payment method", e);
        }

        finally {
            releaseConnection(connection);
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM PaymentMethods WHERE id = ?";

        Connection connection = getConnection();

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, id);

            int rows = stm.executeUpdate();

            if (rows == 0) {
                LOGGER.warn("No payment method found to delete with id {}", id);
            }

        } catch (SQLException e) {
            LOGGER.error("Error deleting payment method with id {}", id, e);
        }

        finally {
            releaseConnection(connection);
        }
    }

    private PaymentMethod mapResultSetToPaymentMethod(ResultSet rs) throws SQLException {
        PaymentMethod pm = new PaymentMethod();

        pm.setId(rs.getLong("id"));
        pm.setMethodName(rs.getString("method_name"));
        pm.setActive(rs.getBoolean("is_active"));
        pm.setCreatedAt(rs.getString("created_at"));

        return pm;
    }
}