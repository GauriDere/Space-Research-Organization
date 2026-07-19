package com.sro.model;

import java.io.Serializable;

/**
 * Model class representing a registered User.
 * Maps to the `users` table in sro_db.
 * Assignment: Login / Registration Module
 */
public class User implements Serializable {

    private static final long serialVersionUID = 2L;

    private int    id;
    private String username;
    private String password;

    // ── Constructors ──────────────────────────────────────────
    public User() {}

    public User(int id, String username, String password) {
        this.id       = id;
        this.username = username;
        this.password = password;
    }

    // ── Getters & Setters ─────────────────────────────────────
    public int    getId()              { return id; }
    public void   setId(int id)        { this.id = id; }

    public String getUsername()        { return username; }
    public void   setUsername(String u){ this.username = u; }

    public String getPassword()        { return password; }
    public void   setPassword(String p){ this.password = p; }

    @Override
    public String toString() {
        return "User{id=" + id + ", username='" + username + "'}";
    }
}
