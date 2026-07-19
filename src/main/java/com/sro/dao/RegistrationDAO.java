package com.sro.dao;

import com.sro.model.Registration;
import java.sql.*;

/**
 * DAO for Cadet Registration against the `registrations` table in sro_db.
 * Assignment: Struts Registration Module
 */
public class RegistrationDAO {

    private static final String DB_URL      = "jdbc:mysql://localhost:3306/sro_db";
    private static final String DB_USER     = "root";
    private static final String DB_PASSWORD = "";   // Change to your MySQL password

    private Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    /**
     * Saves a new cadet registration to the database.
     * @param reg Registration object containing form data.
     * @return true if saved successfully, false otherwise.
     */
    public boolean saveRegistration(Registration reg) {
        String sql = "INSERT INTO registrations (full_name, mobile, email) VALUES (?, ?, ?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, reg.getFullName());
            ps.setString(2, reg.getMobile());
            ps.setString(3, reg.getEmail());
            return ps.executeUpdate() > 0;

        } catch (SQLIntegrityConstraintViolationException e) {
            // Email already registered
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Checks whether an email is already registered.
     * @param email Email to check.
     * @return true if email exists in DB.
     */
    public boolean emailExists(String email) {
        String sql = "SELECT id FROM registrations WHERE email = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
