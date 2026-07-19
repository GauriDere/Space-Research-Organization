package com.sro.dao;

import com.sro.model.SpaceOperation;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object) for SpaceOperation CRUD operations.
 * Assignment: Dynamic Web Application using MySQL
 * Implements Add, Update, Delete, Retrieve against `space_operations` table.
 */
public class SpaceOperationDAO {

    // ── JDBC Connection Details ─────────────────────────────────────────────
    private static final String DB_URL      = "jdbc:mysql://localhost:3306/sro_db";
    private static final String DB_USER     = "root";
    private static final String DB_PASSWORD = "";          // Change to your MySQL password

    // ── Obtain Connection ───────────────────────────────────────────────────
    private Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    // ── CREATE ──────────────────────────────────────────────────────────────
    /**
     * Inserts a new SpaceOperation record into the database.
     * @param op SpaceOperation to insert.
     * @return true if inserted successfully, false otherwise.
     */
    public boolean addOperation(SpaceOperation op) {
        String sql = "INSERT INTO space_operations (project_name, start_date, objective, status) VALUES (?, ?, ?, ?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, op.getProjectName());
            ps.setString(2, op.getStartDate());
            ps.setString(3, op.getObjective());
            ps.setString(4, op.getStatus());
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ── READ (ALL) ──────────────────────────────────────────────────────────
    /**
     * Retrieves all SpaceOperation records from the database.
     * @return List of SpaceOperation objects.
     */
    public List<SpaceOperation> getAllOperations() {
        List<SpaceOperation> list = new ArrayList<>();
        String sql = "SELECT * FROM space_operations ORDER BY id DESC";
        try (Connection con = getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs   = stmt.executeQuery(sql)) {

            while (rs.next()) {
                SpaceOperation op = new SpaceOperation();
                op.setId(rs.getInt("id"));
                op.setProjectName(rs.getString("project_name"));
                op.setStartDate(rs.getString("start_date"));
                op.setObjective(rs.getString("objective"));
                op.setStatus(rs.getString("status"));
                list.add(op);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // ── READ (BY ID) ────────────────────────────────────────────────────────
    /**
     * Retrieves a single SpaceOperation record by its ID.
     * @param id Primary key of the record.
     * @return SpaceOperation object or null if not found.
     */
    public SpaceOperation getOperationById(int id) {
        String sql = "SELECT * FROM space_operations WHERE id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new SpaceOperation(
                        rs.getInt("id"),
                        rs.getString("project_name"),
                        rs.getString("start_date"),
                        rs.getString("objective"),
                        rs.getString("status")
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // ── UPDATE ──────────────────────────────────────────────────────────────
    /**
     * Updates an existing SpaceOperation record.
     * @param op SpaceOperation with updated values (id must be set).
     * @return true if updated successfully, false otherwise.
     */
    public boolean updateOperation(SpaceOperation op) {
        String sql = "UPDATE space_operations SET project_name=?, start_date=?, objective=?, status=? WHERE id=?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, op.getProjectName());
            ps.setString(2, op.getStartDate());
            ps.setString(3, op.getObjective());
            ps.setString(4, op.getStatus());
            ps.setInt(5, op.getId());
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ── DELETE ──────────────────────────────────────────────────────────────
    /**
     * Deletes a SpaceOperation record by ID.
     * @param id Primary key of the record to delete.
     * @return true if deleted successfully, false otherwise.
     */
    public boolean deleteOperation(int id) {
        String sql = "DELETE FROM space_operations WHERE id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
