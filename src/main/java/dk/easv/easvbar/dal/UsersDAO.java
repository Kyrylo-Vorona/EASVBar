package dk.easv.easvbar.dal;

import dk.easv.easvbar.be.EventException;
import dk.easv.easvbar.be.User;

import java.sql.*;

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
                String role = rs.getString("role");
                return new User(id, username, role);
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
}
