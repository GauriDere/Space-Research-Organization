package com.sro.servlet;

import com.sro.dao.UserDAO;
import com.sro.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * LoginServlet — demonstrates use of Servlet with JavaScript Validation (Assignment Point 2 & 6)
 * Handles POST-based login authentication against MySQL users table.
 *
 * URL Mapping : /login
 * On success  : redirects to welcome.jsp with session
 * On failure  : redirects back with error message
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 2L;
    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Server-side validation (in addition to JS client-side validation)
        if (username == null || username.trim().isEmpty()) {
            request.setAttribute("error", "Username is required.");
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
            return;
        }

        if (password == null || password.trim().isEmpty()) {
            request.setAttribute("error", "Password is required.");
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
            return;
        }

        // Authenticate against MySQL
        User user = userDAO.authenticate(username.trim(), password.trim());

        if (user != null) {
            // Create session and store user info
            HttpSession session = request.getSession();
            session.setAttribute("loggedUser", user.getUsername());
            session.setAttribute("userId", user.getId());

            // Redirect to JSP welcome page
            response.sendRedirect(request.getContextPath() + "/welcome");
        } else {
            // Authentication failed — send alert message via request attribute
            request.setAttribute("error", "ACCESS DENIED: Invalid credentials. Please try again.");
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Show the login page
        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }
}
