package dk.easv.easvbar.dal;

import dk.easv.easvbar.be.EventException;
import dk.easv.easvbar.be.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDAO {
    private final ConnectionManager cm;
    public UsersDAO() { cm  = new ConnectionManager(); }

    public User login(String username, String password) throws EventException {
        String sql = "SELECT * FROM Users WHERE username = ? AND password_hash = ?";

        try (Connection con = cm.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String email = rs.getString("email");
                String role = rs.getString("role");
                return new User(id, username, role, email);
            }
        } catch (SQLException e) {
            throw new EventException("Database error: Could not log in", e);
        }
        return null;
    }

    public void addUser(String username, String password, String email, String role) throws EventException {
        try (Connection con = cm.getConnection()) {
            String add = "INSERT INTO Users (username, password_hash, email, role) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(add);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, email);
            pstmt.setString(4, role);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new EventException("Database error: Could not add user", e);
        }
    }

    public void editUser(User user) throws EventException {
        try (Connection con = cm.getConnection()) {
            String add = "UPDATE Users SET username = ?, email = ?, role = ? WHERE id = ?";
            PreparedStatement pstmt = con.prepareStatement(add);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getRole());
            pstmt.setInt(4, user.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new EventException("Database error: Could not edit selected user", e);
        }
    }

    public void deleteUser(User user) throws EventException {
        try (Connection con = cm.getConnection()) {
            String add = "DELETE FROM Users WHERE id = ?";
            PreparedStatement pstmt = con.prepareStatement(add);
            pstmt.setInt(1, user.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new EventException("Database error: Could not delete selected user", e);
        }
    }

    public List<User> getAllUsers() throws EventException {
        List<User> users = new ArrayList<>();
        try (Connection con = cm.getConnection()) {
            String select = "SELECT * FROM Users";
            PreparedStatement pstmt = con.prepareStatement(select);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                users.add(new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("role"),
                        rs.getString("email")
                ));
            }
        } catch (SQLException e) {
            throw new EventException("Could not get the list of movies", e);
        }
        return users;
    }

    public List<User> getAllCoordinators() throws EventException {
        List<User> coordinators = new ArrayList<>();
        String sql = "SELECT * FROM Users WHERE role = 'COORDINATOR'";

        try (Connection con = cm.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                coordinators.add(new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("role"),
                        rs.getString("email")
                ));
            }
        }catch (SQLException e) {
            throw new EventException("Could not get the list of movies", e);
        }
        return coordinators;
    }
}
