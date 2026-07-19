package com.sro.model;

import java.io.Serializable;

/**
 * Model class representing a Space Operation record.
 * Maps to the `space_operations` table in sro_db.
 * Assignment: MySQL Dynamic Web Application - Entity Model
 */
public class SpaceOperation implements Serializable {

    private static final long serialVersionUID = 1L;

    private int    id;
    private String projectName;
    private String startDate;
    private String objective;
    private String status;

    // ── Constructors ──────────────────────────────────────────
    public SpaceOperation() {}

    public SpaceOperation(int id, String projectName, String startDate,
                          String objective, String status) {
        this.id          = id;
        this.projectName = projectName;
        this.startDate   = startDate;
        this.objective   = objective;
        this.status      = status;
    }

    // ── Getters & Setters ─────────────────────────────────────
    public int    getId()          { return id; }
    public void   setId(int id)    { this.id = id; }

    public String getProjectName()              { return projectName; }
    public void   setProjectName(String n)      { this.projectName = n; }

    public String getStartDate()                { return startDate; }
    public void   setStartDate(String d)        { this.startDate = d; }

    public String getObjective()                { return objective; }
    public void   setObjective(String o)        { this.objective = o; }

    public String getStatus()                   { return status; }
    public void   setStatus(String s)           { this.status = s; }

    @Override
    public String toString() {
        return "SpaceOperation{id=" + id + ", projectName='" + projectName +
               "', status='" + status + "'}";
    }
}
