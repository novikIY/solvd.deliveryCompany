package com.solvd.deliverycompany.dao.mysqlimpl;

import com.solvd.deliverycompany.dao.ICustomerDAO;
import com.solvd.deliverycompany.model.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO extends AbstractMySQLDAO implements ICustomerDAO {

    private static final Logger LOGGER = LogManager.getLogger(CustomerDAO.class);

    @Override
    public void create(Customer customer) {
        String sql = "INSERT INTO Customers (email, phone) VALUES (?, ?)";

        Connection connection = getConnection();

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setString(1, customer.getEmail());
            stm.setString(2, customer.getPhone());

            stm.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Error creating customer", e);
        }

        finally {
            releaseConnection(connection);
        }
    }

    @Override
    public Customer getById(Long id) {
        String sql = "SELECT id, email, phone, created_at FROM Customers WHERE id = ?";

        Connection connection = getConnection();

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, id);

            try (ResultSet rs = stm.executeQuery()) {

                if (rs.next()) {
                    return mapResultSetToCustomer(rs);
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting customer by id {}", id, e);
        }

        finally {
            releaseConnection(connection);
        }

        return null;
    }

    @Override
    public List<Customer> getAll() {
        String sql = "SELECT id, email, phone, created_at FROM Customers";
        List<Customer> customers = new ArrayList<>();

        Connection connection = getConnection();

        try (PreparedStatement stm = connection.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                customers.add(mapResultSetToCustomer(rs));
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting all customers", e);
        }

        finally {
            releaseConnection(connection);
        }

        return customers;
    }

    @Override
    public void update(Customer customer) {
        String sql = "UPDATE Customers SET email = ?, phone = ? WHERE id = ?";

        Connection connection = getConnection();

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setString(1, customer.getEmail());
            stm.setString(2, customer.getPhone());

            int rows = stm.executeUpdate();

            if (rows == 0) {
                LOGGER.warn("No customer found with id {}", customer.getId());
            }

        } catch (SQLException e) {
            LOGGER.error("Error updating customer", e);
        }

        finally {
            releaseConnection(connection);
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM Customers WHERE id = ?";

        Connection connection = getConnection();

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, id);

            int rows = stm.executeUpdate();

            if (rows == 0) {
                LOGGER.warn("No customer found to delete with id {}", id);
            }

        } catch (SQLException e) {
            LOGGER.error("Error deleting customer with id {}", id, e);
        }

        finally {
            releaseConnection(connection);
        }
    }

    @Override
    public Customer getByEmail(String email) {
        String sql = "SELECT id, email, phone FROM Customers WHERE email = ?";

        Connection connection = getConnection();

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setString(1, email);

            try (ResultSet rs = stm.executeQuery()) {

                if (rs.next()) {
                    return mapResultSetToCustomer(rs);
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting customer by email {}", email, e);
        }

        finally {
            releaseConnection(connection);
        }

        return null;
    }

    private Customer mapResultSetToCustomer(ResultSet rs) throws SQLException {
        Customer c = new Customer();

        c.setId(rs.getLong("id"));
        c.setEmail(rs.getString("email"));
        c.setPhone(rs.getString("phone"));

        return c;
    }
}