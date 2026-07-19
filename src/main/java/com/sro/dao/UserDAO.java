package com.sro.dao;

import com.sro.model.User;
import java.sql.*;

/**
 * DAO for User authentication against the `users` table in sro_db.
 * Assignment: Login with MySQL
 */
public class UserDAO {

    private static final String DB_URL      = "jdbc:mysql://localhost:3306/sro_db";
    private static final String DB_USER     = "root";
    private static final String DB_PASSWORD = "";   // Change to your MySQL password

    private Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    /**
     * Validates username and password against the database.
     * @return User object if found, null if credentials are wrong.
     */
    public User authenticate(String username, String password) {
        String sql = "SELECT * FROM users WHERE username=? AND password=?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password")
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Inserts a new user record.
     * @return true if inserted, false if username already exists or error.
     */
    public boolean registerUser(String username, String password) {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);
            return ps.executeUpdate() > 0;

        } catch (SQLIntegrityConstraintViolationException e) {
            // Username already exists
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
