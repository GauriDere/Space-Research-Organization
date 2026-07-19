package com.sro.servlet;

import com.sro.dao.RegistrationDAO;
import com.sro.model.Registration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * RegistrationServlet — demonstrates Struts-style Registration with validations (Assignment Point 3 & 6)
 * Handles POST-based form submission with server-side validations.
 * Re-displays form on error, shows congratulations page on success.
 *
 * URL Mapping : /register
 */
@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {

    private static final long serialVersionUID = 3L;
    private final RegistrationDAO regDAO = new RegistrationDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Show registration form
        request.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String fullName = request.getParameter("fullName");
        String mobile   = request.getParameter("mobile");
        String email    = request.getParameter("email");

        // ── Validations (a through e) ─────────────────────────────────────
        StringBuilder errors = new StringBuilder();

        // a. Name validation
        if (fullName == null || fullName.trim().isEmpty()) {
            errors.append("Name is required.<br>");
        } else if (!fullName.trim().matches("[A-Za-z ]{2,}")) {
            errors.append("Name must contain only alphabets and spaces (min 2 characters).<br>");
        }

        // b. Mobile validation
        if (mobile == null || mobile.trim().isEmpty()) {
            errors.append("Mobile number is required.<br>");
        } else if (!mobile.trim().matches("[0-9]{10}")) {
            errors.append("Mobile number must be exactly 10 digits.<br>");
        }

        // c. Email validation
        if (email == null || email.trim().isEmpty()) {
            errors.append("Email ID is required.<br>");
        } else if (!email.trim().matches("^[A-Za-z0-9._%+\\-]+@[A-Za-z0-9.\\-]+\\.[A-Za-z]{2,}$")) {
            errors.append("Email ID format is invalid.<br>");
        }

        // d. All fields empty check
        if (errors.length() == 0 && (fullName.trim().isEmpty() && mobile.trim().isEmpty() && email.trim().isEmpty())) {
            errors.append("Please fill in all required fields.<br>");
        }

        // e. Re-display form if there are errors
        if (errors.length() > 0) {
            request.setAttribute("errors",   errors.toString());
            request.setAttribute("fullName", fullName);
            request.setAttribute("mobile",   mobile);
            request.setAttribute("email",    email);
            request.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(request, response);
            return;
        }

        // ── Save to DB ────────────────────────────────────────────────────
        Registration reg = new Registration();
        reg.setFullName(fullName.trim());
        reg.setMobile(mobile.trim());
        reg.setEmail(email.trim());

        boolean saved = regDAO.saveRegistration(reg);

        if (saved) {
            // f. Congratulations page on success
            request.setAttribute("fullName", fullName.trim());
            request.getRequestDispatcher("/WEB-INF/views/welcome_registration.jsp").forward(request, response);
        } else {
            request.setAttribute("errors", "This email is already registered. Please use a different email.<br>");
            request.setAttribute("fullName", fullName);
            request.setAttribute("mobile",   mobile);
            request.setAttribute("email",    email);
            request.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(request, response);
        }
    }
}
