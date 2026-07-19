package com.sro.servlet;

import com.sro.ejb.SpaceOperationServiceLocal;
import com.sro.ejb.SpaceOperationServiceBean;
import com.sro.model.SpaceOperation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * WelcomeServlet — Serves the JSP welcome dashboard after login.
 * Demonstrates EJB injection into a Servlet (Assignment Points 6 & 9).
 * Fetches mission status counts from the EJB business layer.
 * URL Mapping: /welcome
 */
@WebServlet("/welcome")
public class WelcomeServlet extends HttpServlet {

    private static final long serialVersionUID = 5L;

    // In a full Java EE server (GlassFish/WildFly), use @EJB injection:
    // @EJB
    // private SpaceOperationServiceLocal operationService;
    //
    // For Tomcat (no EJB container), we directly instantiate the bean:
    private final SpaceOperationServiceLocal operationService = new SpaceOperationServiceBean();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Check session
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedUser") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Use EJB business layer to get stats (demonstrates EJB usage)
        long plannedCount    = operationService.countByStatus("Planned");
        long inProgressCount = operationService.countByStatus("In Progress");
        long completedCount  = operationService.countByStatus("Completed");

        request.setAttribute("plannedCount",    plannedCount);
        request.setAttribute("inProgressCount", inProgressCount);
        request.setAttribute("completedCount",  completedCount);

        request.getRequestDispatcher("/WEB-INF/views/welcome.jsp").forward(request, response);
    }
}
