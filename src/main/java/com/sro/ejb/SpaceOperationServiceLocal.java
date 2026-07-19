package com.sro.ejb;

import com.sro.model.SpaceOperation;
import java.util.List;
import javax.ejb.Local;

/**
 * SpaceOperationServiceLocal — EJB Local Business Interface (Assignment Point 9)
 * Defines the business contract for Space Operations management.
 * This is a Local interface used within the same JVM (same EAR/WAR deployment).
 */
@Local
public interface SpaceOperationServiceLocal {

    /**
     * Adds a new space operation using business logic.
     * Validates that project name is not empty and status is a known value.
     * @param op SpaceOperation to add.
     * @return true if operation added successfully.
     */
    boolean addOperation(SpaceOperation op);

    /**
     * Retrieves all space operations.
     * @return List of all SpaceOperation records.
     */
    List<SpaceOperation> getAllOperations();

    /**
     * Retrieves a specific space operation by ID.
     * @param id Operation ID.
     * @return SpaceOperation or null if not found.
     */
    SpaceOperation getOperationById(int id);

    /**
     * Updates an existing space operation.
     * Applies business validation before persisting.
     * @param op SpaceOperation with updated data.
     * @return true if updated successfully.
     */
    boolean updateOperation(SpaceOperation op);

    /**
     * Deletes a space operation by ID.
     * @param id Operation ID to delete.
     * @return true if deleted successfully.
     */
    boolean deleteOperation(int id);

    /**
     * Returns count of operations by status (business logic).
     * @param status Status to filter: "Planned", "In Progress", "Completed".
     * @return count of matching records.
     */
    long countByStatus(String status);
}
