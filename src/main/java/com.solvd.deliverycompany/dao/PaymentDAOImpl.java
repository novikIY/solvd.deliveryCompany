package com.solvd.deliverycompany.dao;

import com.solvd.deliverycompany.model.Order;
import com.solvd.deliverycompany.model.Payment;
import com.solvd.deliverycompany.model.PaymentMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAOImpl extends AbstractDAO<Payment> implements IPaymentDAO {

    private static final Logger LOGGER = LogManager.getLogger(PaymentDAOImpl.class);

    public PaymentDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Payment payment) {
        String sql = "INSERT INTO Payments (order_id, payment_method_id, amount, status, paid_at, created_at) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, payment.getOrder().getId());
            stm.setLong(2, payment.getPaymentMethod().getId());
            stm.setDouble(3, payment.getAmount());
            stm.setString(4, payment.getStatus());
            stm.setString(5, payment.getPaidAt());
            stm.setString(6, payment.getCreatedAt());

            stm.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Error creating payment", e);
        }
    }

    @Override
    public Payment getById(Long id) {
        String sql = "SELECT id, order_id, payment_method_id, amount, status, paid_at, created_at FROM Payments WHERE id = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, id);

            try (ResultSet rs = stm.executeQuery()) {

                if (rs.next()) {
                    Payment p = new Payment();

                    p.setId(rs.getLong("id"));

                    Order order = new Order();
                    order.setId(rs.getLong("order_id"));
                    p.setOrder(order);

                    PaymentMethod pm = new PaymentMethod();
                    pm.setId(rs.getLong("payment_method_id"));
                    p.setPaymentMethod(pm);

                    p.setAmount(rs.getDouble("amount"));
                    p.setStatus(rs.getString("status"));
                    p.setPaidAt(rs.getString("paid_at"));
                    p.setCreatedAt(rs.getString("created_at"));

                    return p;
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting payment by id {}", id, e);
        }

        return null;
    }

    @Override
    public List<Payment> getAll() {
        String sql = "SELECT id, order_id, payment_method_id, amount, status, paid_at, created_at FROM Payments";
        List<Payment> payments = new ArrayList<>();

        try (PreparedStatement stm = connection.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                Payment p = new Payment();

                p.setId(rs.getLong("id"));

                Order order = new Order();
                order.setId(rs.getLong("order_id"));
                p.setOrder(order);

                PaymentMethod pm = new PaymentMethod();
                pm.setId(rs.getLong("payment_method_id"));
                p.setPaymentMethod(pm);

                p.setAmount(rs.getDouble("amount"));
                p.setStatus(rs.getString("status"));
                p.setPaidAt(rs.getString("paid_at"));
                p.setCreatedAt(rs.getString("created_at"));

                payments.add(p);
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting all payments", e);
        }

        return payments;
    }

    @Override
    public void update(Payment payment) {
        String sql = "UPDATE Payments SET order_id = ?, payment_method_id = ?, amount = ?, status = ?, paid_at = ?, created_at = ? WHERE id = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, payment.getOrder().getId());
            stm.setLong(2, payment.getPaymentMethod().getId());
            stm.setDouble(3, payment.getAmount());
            stm.setString(4, payment.getStatus());
            stm.setString(5, payment.getPaidAt());
            stm.setString(6, payment.getCreatedAt());
            stm.setLong(7, payment.getId());

            int rows = stm.executeUpdate();

            if (rows == 0) {
                LOGGER.warn("No payment found with id {}", payment.getId());
            }

        } catch (SQLException e) {
            LOGGER.error("Error updating payment", e);
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM Payments WHERE id = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, id);

            int rows = stm.executeUpdate();

            if (rows == 0) {
                LOGGER.warn("No payment found to delete with id {}", id);
            }

        } catch (SQLException e) {
            LOGGER.error("Error deleting payment with id {}", id, e);
        }
    }

    @Override
    public List<Payment> getByOrderId(Long orderId) {
        String sql = "SELECT id, order_id, payment_method_id, amount, status, paid_at, created_at FROM Payments WHERE order_id = ?";
        List<Payment> payments = new ArrayList<>();

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, orderId);

            try (ResultSet rs = stm.executeQuery()) {

                while (rs.next()) {
                    Payment p = new Payment();

                    p.setId(rs.getLong("id"));

                    Order order = new Order();
                    order.setId(rs.getLong("order_id"));
                    p.setOrder(order);

                    PaymentMethod pm = new PaymentMethod();
                    pm.setId(rs.getLong("payment_method_id"));
                    p.setPaymentMethod(pm);

                    p.setAmount(rs.getDouble("amount"));
                    p.setStatus(rs.getString("status"));
                    p.setPaidAt(rs.getString("paid_at"));
                    p.setCreatedAt(rs.getString("created_at"));

                    payments.add(p);
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting payments by orderId {}", orderId, e);
        }

        return payments;
    }
}