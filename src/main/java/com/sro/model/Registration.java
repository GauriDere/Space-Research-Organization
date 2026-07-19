package com.sro.model;

import java.io.Serializable;

/**
 * Model class representing a Cadet Registration.
 * Maps to the `registrations` table in sro_db.
 * Assignment: Struts Registration with Validations
 */
public class Registration implements Serializable {

    private static final long serialVersionUID = 3L;

    private int    id;
    private String fullName;
    private String mobile;
    private String email;

    // ── Constructors ──────────────────────────────────────────
    public Registration() {}

    public Registration(int id, String fullName, String mobile, String email) {
        this.id       = id;
        this.fullName = fullName;
        this.mobile   = mobile;
        this.email    = email;
    }

    // ── Getters & Setters ─────────────────────────────────────
    public int    getId()              { return id; }
    public void   setId(int id)        { this.id = id; }

    public String getFullName()        { return fullName; }
    public void   setFullName(String n){ this.fullName = n; }

    public String getMobile()          { return mobile; }
    public void   setMobile(String m)  { this.mobile = m; }

    public String getEmail()           { return email; }
    public void   setEmail(String e)   { this.email = e; }

    @Override
    public String toString() {
        return "Registration{id=" + id + ", fullName='" + fullName +
               "', email='" + email + "'}";
    }
}
