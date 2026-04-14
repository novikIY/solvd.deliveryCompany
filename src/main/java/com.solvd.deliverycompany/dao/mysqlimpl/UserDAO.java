package com.solvd.deliverycompany.dao.mysqlimpl;

import com.solvd.deliverycompany.dao.IUserDAO;
import com.solvd.deliverycompany.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends AbstractMySQLDAO implements IUserDAO {

    private static final Logger LOGGER = LogManager.getLogger(UserDAO.class);

    @Override
    public void create(User user) {
        String sql = "INSERT INTO Users (first_name, last_name, role, created_at) VALUES (?, ?, ?, ?)";

        Connection connection = getConnection();

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setString(1, user.getFirstName());
            stm.setString(2, user.getLastName());
            stm.setString(3, user.getRole());
            stm.setString(4, user.getCreatedAt());

            stm.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Error creating user", e);
        }

        finally {
            releaseConnection(connection);
        }
    }

    @Override
    public User getById(Long id) {
        String sql = "SELECT id, first_name, last_name, role, created_at FROM Users WHERE id = ?";

        Connection connection = getConnection();

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, id);

            try (ResultSet rs = stm.executeQuery()) {

                if (rs.next()) {
                    return mapResultSetToUser(rs);
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting user by id {}", id, e);
        }

        finally {
            releaseConnection(connection);
        }

        return null;
    }

    @Override
    public List<User> getAll() {
        String sql = "SELECT id, first_name, last_name, role, created_at FROM Users";
        List<User> users = new ArrayList<>();

        Connection connection = getConnection();

        try (PreparedStatement stm = connection.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                users.add(mapResultSetToUser(rs));
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting all users", e);
        }

        finally {
            releaseConnection(connection);
        }

        return users;
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE Users SET first_name = ?, last_name = ?, role = ?, created_at = ? WHERE id = ?";

        Connection connection = getConnection();

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setString(1, user.getFirstName());
            stm.setString(2, user.getLastName());
            stm.setString(3, user.getRole());
            stm.setString(4, user.getCreatedAt());
            stm.setLong(5, user.getId());

            int rows = stm.executeUpdate();

            if (rows == 0) {
                LOGGER.warn("No user found with id {}", user.getId());
            }

        } catch (SQLException e) {
            LOGGER.error("Error updating user", e);
        }

        finally {
            releaseConnection(connection);
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM Users WHERE id = ?";

        Connection connection = getConnection();

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, id);

            int rows = stm.executeUpdate();

            if (rows == 0) {
                LOGGER.warn("No user found to delete with id {}", id);
            }

        } catch (SQLException e) {
            LOGGER.error("Error deleting user with id {}", id, e);
        }

        finally {
            releaseConnection(connection);
        }
    }

    @Override
    public List<User> getByFirstName(String firstName) {
        String sql = "SELECT id, first_name, last_name, role, created_at FROM Users WHERE first_name = ?";
        List<User> users = new ArrayList<>();

        Connection connection = getConnection();

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setString(1, firstName);

            try (ResultSet rs = stm.executeQuery()) {

                while (rs.next()) {
                    users.add(mapResultSetToUser(rs));
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting users by first name", e);
        }

        finally {
            releaseConnection(connection);
        }

        return users;
    }

    @Override
    public List<User> getByLastName(String lastName) {
        String sql = "SELECT id, first_name, last_name, role, created_at FROM Users WHERE last_name = ?";
        List<User> users = new ArrayList<>();

        Connection connection = getConnection();

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setString(1, lastName);

            try (ResultSet rs = stm.executeQuery()) {

                while (rs.next()) {
                    users.add(mapResultSetToUser(rs));
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting users by last name", e);
        }

        finally {
            releaseConnection(connection);
        }

        return users;
    }

    @Override
    public List<User> getByRole(String role) {
        String sql = "SELECT id, first_name, last_name, role, created_at FROM Users WHERE role = ?";
        List<User> users = new ArrayList<>();

        Connection connection = getConnection();

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setString(1, role);

            try (ResultSet rs = stm.executeQuery()) {

                while (rs.next()) {
                    users.add(mapResultSetToUser(rs));
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Error getting users by role", e);
        }

        finally {
            releaseConnection(connection);
        }

        return users;
    }

    private User mapResultSetToUser(ResultSet rs) throws SQLException {
        User u = new User();

        u.setId(rs.getLong("id"));
        u.setFirstName(rs.getString("first_name"));
        u.setLastName(rs.getString("last_name"));
        u.setRole(rs.getString("role"));
        u.setCreatedAt(rs.getString("created_at"));

        return u;
    }
}