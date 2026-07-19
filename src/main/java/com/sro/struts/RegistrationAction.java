package com.sro.struts;

import com.sro.dao.RegistrationDAO;
import com.sro.model.Registration;

/**
 * RegistrationAction — Struts-style Action class (Assignment Point 3)
 * Simulates a Struts 1.x ActionForm + Action pattern for Registration.
 * Holds form field properties, validation logic, and execute() method.
 *
 * Note: In a real Struts 1.x project, this class would extend Action and
 * ActionForm. Here it is demonstrated as a POJO action class for clarity.
 */
public class RegistrationAction {

    // ── Form Fields (ActionForm properties) ─────────────────────────────────
    private String fullName;
    private String mobile;
    private String email;

    // ── Error messages ───────────────────────────────────────────────────────
    private String nameError;
    private String mobileError;
    private String emailError;
    private String successMessage;

    // ── DAO ──────────────────────────────────────────────────────────────────
    private final RegistrationDAO regDAO = new RegistrationDAO();

    // ── Validation (simulates Struts ActionForm.validate()) ──────────────────
    /**
     * Validates all registration form fields.
     * @return true if form is valid, false otherwise.
     */
    public boolean validate() {
        boolean valid = true;

        // a. Name validation
        if (fullName == null || fullName.trim().isEmpty()) {
            nameError = "Name is required.";
            valid = false;
        } else if (!fullName.trim().matches("[A-Za-z ]{2,}")) {
            nameError = "Name must contain only letters and spaces (min 2 chars).";
            valid = false;
        }

        // b. Mobile validation
        if (mobile == null || mobile.trim().isEmpty()) {
            mobileError = "Mobile number is required.";
            valid = false;
        } else if (!mobile.trim().matches("[0-9]{10}")) {
            mobileError = "Mobile number must be exactly 10 digits.";
            valid = false;
        }

        // c. Email validation
        if (email == null || email.trim().isEmpty()) {
            emailError = "Email ID is required.";
            valid = false;
        } else if (!email.trim().matches("^[A-Za-z0-9._%+\\-]+@[A-Za-z0-9.\\-]+\\.[A-Za-z]{2,}$")) {
            emailError = "Email ID format is invalid (e.g. user@example.com).";
            valid = false;
        }

        return valid;
    }

    // ── Execute (simulates Struts Action.execute()) ───────────────────────────
    /**
     * Processes the registration if validation passes.
     * @return "success" if registration saved, "input" if validation fails, "error" on DB failure.
     */
    public String execute() {
        // d. Check for empty form
        if ((fullName == null || fullName.trim().isEmpty()) &&
            (mobile   == null || mobile.trim().isEmpty())   &&
            (email    == null || email.trim().isEmpty())) {
            nameError   = "Name is required.";
            mobileError = "Mobile number is required.";
            emailError  = "Email ID is required.";
            return "input";
        }

        if (!validate()) {
            return "input";  // e. Re-display form with errors
        }

        // Check for duplicate email
        if (regDAO.emailExists(email.trim())) {
            emailError = "This email is already registered.";
            return "input";
        }

        // Save to database
        Registration reg = new Registration();
        reg.setFullName(fullName.trim());
        reg.setMobile(mobile.trim());
        reg.setEmail(email.trim());

        boolean saved = regDAO.saveRegistration(reg);

        if (saved) {
            // f. Success message
            successMessage = "Congratulations " + fullName.trim() +
                             "! Welcome to Space Research Organization.";
            return "success";
        }

        return "error";
    }

    // ── Getters & Setters ─────────────────────────────────────────────────────
    public String getFullName()       { return fullName; }
    public void   setFullName(String n){ this.fullName = n; }

    public String getMobile()         { return mobile; }
    public void   setMobile(String m) { this.mobile = m; }

    public String getEmail()          { return email; }
    public void   setEmail(String e)  { this.email = e; }

    public String getNameError()      { return nameError; }
    public String getMobileError()    { return mobileError; }
    public String getEmailError()     { return emailError; }
    public String getSuccessMessage() { return successMessage; }
}
