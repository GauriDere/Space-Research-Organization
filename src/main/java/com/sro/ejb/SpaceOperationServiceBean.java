package com.sro.ejb;

import com.sro.dao.SpaceOperationDAO;
import com.sro.model.SpaceOperation;

import javax.ejb.Stateless;
import java.util.Arrays;
import java.util.List;

/**
 * SpaceOperationServiceBean — EJB Stateless Session Bean (Assignment Point 9)
 * Implements the SpaceOperationServiceLocal business interface.
 * Contains all business logic for managing Space Operations.
 *
 * @Stateless: No client-specific state is maintained between calls.
 *             The container manages the lifecycle of bean instances.
 */
@Stateless
public class SpaceOperationServiceBean implements SpaceOperationServiceLocal {

    // ── Allowed status values (Business Rule) ───────────────────────────────
    private static final List<String> VALID_STATUSES =
            Arrays.asList("Planned", "In Progress", "Completed");

    // ── DAO for DB interaction ───────────────────────────────────────────────
    private final SpaceOperationDAO dao = new SpaceOperationDAO();

    // ── Business Method: Add ─────────────────────────────────────────────────
    @Override
    public boolean addOperation(SpaceOperation op) {
        // Business Rule 1: Project name cannot be null or blank
        if (op.getProjectName() == null || op.getProjectName().trim().isEmpty()) {
            throw new IllegalArgumentException("Project name is required.");
        }

        // Business Rule 2: Status must be one of the valid values
        if (!VALID_STATUSES.contains(op.getStatus())) {
            throw new IllegalArgumentException(
                "Invalid status. Must be one of: " + VALID_STATUSES);
        }

        // Business Rule 3: Start date cannot be null
        if (op.getStartDate() == null || op.getStartDate().trim().isEmpty()) {
            throw new IllegalArgumentException("Start date is required.");
        }

        return dao.addOperation(op);
    }

    // ── Business Method: Get All ─────────────────────────────────────────────
    @Override
    public List<SpaceOperation> getAllOperations() {
        return dao.getAllOperations();
    }

    // ── Business Method: Get By ID ───────────────────────────────────────────
    @Override
    public SpaceOperation getOperationById(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Operation ID must be a positive integer.");
        }
        return dao.getOperationById(id);
    }

    // ── Business Method: Update ──────────────────────────────────────────────
    @Override
    public boolean updateOperation(SpaceOperation op) {
        // Business Rule: ID must be valid
        if (op.getId() <= 0) {
            throw new IllegalArgumentException("Valid operation ID is required for update.");
        }

        // Business Rule: Project name cannot be blank
        if (op.getProjectName() == null || op.getProjectName().trim().isEmpty()) {
            throw new IllegalArgumentException("Project name is required.");
        }

        // Business Rule: Status validation
        if (!VALID_STATUSES.contains(op.getStatus())) {
            throw new IllegalArgumentException(
                "Invalid status. Must be one of: " + VALID_STATUSES);
        }

        return dao.updateOperation(op);
    }

    // ── Business Method: Delete ──────────────────────────────────────────────
    @Override
    public boolean deleteOperation(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Valid operation ID is required for deletion.");
        }
        return dao.deleteOperation(id);
    }

    // ── Business Method: Count By Status ────────────────────────────────────
    @Override
    public long countByStatus(String status) {
        List<SpaceOperation> all = dao.getAllOperations();
        return all.stream()
                  .filter(op -> op.getStatus() != null && op.getStatus().equalsIgnoreCase(status))
                  .count();
    }
}
