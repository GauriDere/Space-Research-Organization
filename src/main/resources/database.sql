-- ============================================================
--  SRO (Space Research Organization) - MySQL Database Setup
--  Assignment: Dynamic Web Application using MySQL
-- ============================================================

CREATE DATABASE IF NOT EXISTS sro_db;
USE sro_db;

-- -------------------------------------------------------
-- Table 1: space_operations  (CRUD target table)
-- -------------------------------------------------------
CREATE TABLE IF NOT EXISTS space_operations (
    id           INT AUTO_INCREMENT PRIMARY KEY,
    project_name VARCHAR(100) NOT NULL,
    start_date   DATE,
    objective    TEXT,
    status       VARCHAR(50)
);

-- -------------------------------------------------------
-- Table 2: users  (Login / Authentication)
-- -------------------------------------------------------
CREATE TABLE IF NOT EXISTS users (
    id         INT AUTO_INCREMENT PRIMARY KEY,
    username   VARCHAR(50)  NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- -------------------------------------------------------
-- Table 3: registrations  (Cadet / Internship Registration)
-- -------------------------------------------------------
CREATE TABLE IF NOT EXISTS registrations (
    id           INT AUTO_INCREMENT PRIMARY KEY,
    full_name    VARCHAR(100) NOT NULL,
    mobile       VARCHAR(15)  NOT NULL,
    email        VARCHAR(100) NOT NULL UNIQUE,
    registered_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- -------------------------------------------------------
-- Sample Data: space_operations
-- -------------------------------------------------------
INSERT INTO space_operations (project_name, start_date, objective, status)
VALUES
    ('Project Mercury',     '1983-05-12', 'Exploring Planet Mercury for life and elements for life creation', 'Completed'),
    ('Project Venus',       '1986-11-04', 'Investigating Venus atmosphere dynamics',                          'Completed'),
    ('Lunar Habitat Alpha', '2027-08-15', 'Establishment of a permanent human presence on the Lunar South Pole.', 'In Progress'),
    ('Mars Rover Curiosity II', '2030-05-20', 'Advanced geological sampling and water extraction.',           'Planned'),
    ('Jupiter Orbiter',    '2035-11-11', 'Analyze the atmosphere and magnetic fields of Jupiter.',            'Planned');

-- -------------------------------------------------------
-- Sample Data: users
-- -------------------------------------------------------
INSERT INTO users (username, password) VALUES ('admin', 'admin123');
INSERT INTO users (username, password) VALUES ('orion', 'space2026');
