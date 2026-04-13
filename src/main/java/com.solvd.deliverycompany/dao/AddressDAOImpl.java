package com.solvd.deliverycompany.dao;

import com.solvd.deliverycompany.model.Address;
import com.solvd.deliverycompany.model.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressDAOImpl extends AbstractDAO<Address> implements IAddressDAO {

    private static final Logger LOGGER = LogManager.getLogger(AddressDAOImpl.class);

    public AddressDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Address address) {
        String sql = "INSERT INTO Addresses (customer_id, street, city, postal_code, country) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, address.getCustomer().getId());
            stm.setString(2, address.getStreet());
            stm.setString(3, address.getCity());
            stm.setString(4, address.getPostalCode());
            stm.setString(5, address.getCountry());

            stm.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Error creating address", e);
        }
    }

    @Override
    public Address getById(Long id) {
        String sql = "SELECT id, customer_id, street, city, postal_code, country FROM Addresses WHERE id = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, id);

            try (ResultSet rs = stm.executeQuery()) {

                if (rs.next()) {
                    Address a = new Address();

                    a.setId(rs.getLong("id"));

                    Customer c = new Customer();
                    c.setId(rs.getLong("customer_id"));
                    a.setCustomer(c);

                    a.setStreet(rs.getString("street"));
                    a.setCity(rs.getString("city"));
                    a.setPostalCode(rs.getString("postal_code"));
                    a.setCountry(rs.getString("country"));

                    return a;
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting address by id {}", id, e);
        }

        return null;
    }

    @Override
    public List<Address> getAll() {
        String sql = "SELECT id, customer_id, street, city, postal_code, country FROM Addresses";
        List<Address> list = new ArrayList<>();

        try (PreparedStatement stm = connection.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                Address a = new Address();

                a.setId(rs.getLong("id"));

                Customer c = new Customer();
                c.setId(rs.getLong("customer_id"));
                a.setCustomer(c);

                a.setStreet(rs.getString("street"));
                a.setCity(rs.getString("city"));
                a.setPostalCode(rs.getString("postal_code"));
                a.setCountry(rs.getString("country"));

                list.add(a);
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting all addresses", e);
        }

        return list;
    }

    @Override
    public void update(Address address) {
        String sql = "UPDATE Addresses SET customer_id = ?, street = ?, city = ?, postal_code = ?, country = ? WHERE id = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, address.getCustomer().getId());
            stm.setString(2, address.getStreet());
            stm.setString(3, address.getCity());
            stm.setString(4, address.getPostalCode());
            stm.setString(5, address.getCountry());
            stm.setLong(6, address.getId());

            int rows = stm.executeUpdate();

            if (rows == 0) {
                LOGGER.warn("No address found with id {}", address.getId());
            }

        } catch (SQLException e) {
            LOGGER.error("Error updating address", e);
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM Addresses WHERE id = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, id);

            int rows = stm.executeUpdate();

            if (rows == 0) {
                LOGGER.warn("No address found to delete with id {}", id);
            }

        } catch (SQLException e) {
            LOGGER.error("Error deleting address", e);
        }
    }


    @Override
    public List<Address> getByCustomerId(Long customerId) {
        String sql = "SELECT id, customer_id, street, city, postal_code, country FROM Addresses WHERE customer_id = ?";
        List<Address> list = new ArrayList<>();

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, customerId);

            try (ResultSet rs = stm.executeQuery()) {

                while (rs.next()) {
                    Address a = new Address();

                    a.setId(rs.getLong("id"));

                    Customer c = new Customer();
                    c.setId(rs.getLong("customer_id"));
                    a.setCustomer(c);

                    a.setStreet(rs.getString("street"));
                    a.setCity(rs.getString("city"));
                    a.setPostalCode(rs.getString("postal_code"));
                    a.setCountry(rs.getString("country"));

                    list.add(a);
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting addresses by customerId {}", customerId, e);
        }

        return list;
    }

    @Override
    public List<Address> getByCity(String city) {
        String sql = "SELECT id, customer_id, street, city, postal_code, country FROM Addresses WHERE city = ?";
        List<Address> list = new ArrayList<>();

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setString(1, city);

            try (ResultSet rs = stm.executeQuery()) {

                while (rs.next()) {
                    Address a = new Address();

                    a.setId(rs.getLong("id"));

                    Customer c = new Customer();
                    c.setId(rs.getLong("customer_id"));
                    a.setCustomer(c);

                    a.setStreet(rs.getString("street"));
                    a.setCity(rs.getString("city"));
                    a.setPostalCode(rs.getString("postal_code"));
                    a.setCountry(rs.getString("country"));

                    list.add(a);
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting addresses by city {}", city, e);
        }

        return list;
    }

    @Override
    public Address getByPostalCode(String postalCode) {
        String sql = "SELECT id, customer_id, street, city, postal_code, country FROM Addresses WHERE postal_code = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setString(1, postalCode);

            try (ResultSet rs = stm.executeQuery()) {

                if (rs.next()) {
                    Address a = new Address();

                    a.setId(rs.getLong("id"));

                    Customer c = new Customer();
                    c.setId(rs.getLong("customer_id"));
                    a.setCustomer(c);

                    a.setStreet(rs.getString("street"));
                    a.setCity(rs.getString("city"));
                    a.setPostalCode(rs.getString("postal_code"));
                    a.setCountry(rs.getString("country"));

                    return a;
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting address by postalCode {}", postalCode, e);
        }

        return null;
    }

    @Override
    public void deleteByCustomerId(Long customerId) {
        String sql = "DELETE FROM Addresses WHERE customer_id = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, customerId);

            int rows = stm.executeUpdate();

            if (rows == 0) {
                LOGGER.warn("No addresses found for customerId {}", customerId);
            }

        } catch (SQLException e) {
            LOGGER.error("Error deleting addresses by customerId {}", customerId, e);
        }
    }
}