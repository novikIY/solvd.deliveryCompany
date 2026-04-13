package com.solvd.deliverycompany.dao;

import com.solvd.deliverycompany.model.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl extends AbstractDAO<Customer> implements ICustomerDAO {

    private static final Logger LOGGER = LogManager.getLogger(CustomerDAOImpl.class);

    public CustomerDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Customer customer) {
        String sql = "INSERT INTO Customers (name, email, phone, created_at) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setString(1, customer.getName());
            stm.setString(2, customer.getEmail());
            stm.setString(3, customer.getPhone());
            stm.setString(4, customer.getCreatedAt());

            stm.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Error creating customer", e);
        }
    }

    @Override
    public Customer getById(Long id) {
        String sql = "SELECT id, name, email, phone, created_at FROM Customers WHERE id = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, id);

            try (ResultSet rs = stm.executeQuery()) {

                if (rs.next()) {
                    Customer c = new Customer();
                    c.setId(rs.getLong("id"));
                    c.setName(rs.getString("name"));
                    c.setEmail(rs.getString("email"));
                    c.setPhone(rs.getString("phone"));
                    c.setCreatedAt(rs.getString("created_at"));

                    return c;
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting customer by id {}", id, e);
        }

        return null;
    }

    @Override
    public List<Customer> getAll() {
        String sql = "SELECT id, name, email, phone, created_at FROM Customers";
        List<Customer> customers = new ArrayList<>();

        try (PreparedStatement stm = connection.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                Customer c = new Customer();
                c.setId(rs.getLong("id"));
                c.setName(rs.getString("name"));
                c.setEmail(rs.getString("email"));
                c.setPhone(rs.getString("phone"));
                c.setCreatedAt(rs.getString("created_at"));

                customers.add(c);
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting all customers", e);
        }

        return customers;
    }

    @Override
    public void update(Customer customer) {
        String sql = "UPDATE Customers SET name = ?, email = ?, phone = ?, created_at = ? WHERE id = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setString(1, customer.getName());
            stm.setString(2, customer.getEmail());
            stm.setString(3, customer.getPhone());
            stm.setString(4, customer.getCreatedAt());
            stm.setLong(5, customer.getId());

            int rows = stm.executeUpdate();

            if (rows == 0) {
                LOGGER.warn("No customer found with id {}", customer.getId());
            }

        } catch (SQLException e) {
            LOGGER.error("Error updating customer", e);
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM Customers WHERE id = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, id);

            int rows = stm.executeUpdate();

            if (rows == 0) {
                LOGGER.warn("No customer found to delete with id {}", id);
            }

        } catch (SQLException e) {
            LOGGER.error("Error deleting customer with id {}", id, e);
        }
    }

    @Override
    public Customer getByEmail(String email) {
        String sql = "SELECT id, name, email, phone, created_at FROM Customers WHERE email = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setString(1, email);

            try (ResultSet rs = stm.executeQuery()) {

                if (rs.next()) {
                    Customer c = new Customer();
                    c.setId(rs.getLong("id"));
                    c.setName(rs.getString("name"));
                    c.setEmail(rs.getString("email"));
                    c.setPhone(rs.getString("phone"));
                    c.setCreatedAt(rs.getString("created_at"));

                    return c;
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting customer by email {}", email, e);
        }

        return null;
    }
}