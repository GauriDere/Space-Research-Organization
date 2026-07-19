package com.sro.servlet;

import com.sro.dao.SpaceOperationDAO;
import com.sro.model.SpaceOperation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

/**
 * SpaceOperationsServlet — demonstrates use of Servlet (Assignment Point 6)
 * Handles full CRUD for space_operations via HTTP GET/POST.
 *
 * URL Mapping :  /operations
 * Actions     :  add | update | delete | (default = list all)
 */
@WebServlet("/operations")
public class SpaceOperationsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final SpaceOperationDAO dao = new SpaceOperationDAO();

    // ── GET : List all operations ────────────────────────────────────────────
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            // Handle DELETE via GET (link-based)
            int id = Integer.parseInt(request.getParameter("id"));
            dao.deleteOperation(id);
            response.sendRedirect(request.getContextPath() + "/operations");
            return;
        }

        if ("edit".equals(action)) {
            // Pre-fill form for editing
            int id = Integer.parseInt(request.getParameter("id"));
            SpaceOperation op = dao.getOperationById(id);
            request.setAttribute("editOp", op);
        }

        // Retrieve and display all records
        List<SpaceOperation> ops = dao.getAllOperations();
        request.setAttribute("operations", ops);
        request.getRequestDispatcher("/WEB-INF/views/operations.jsp").forward(request, response);
    }

    // ── POST : Add or Update operation ──────────────────────────────────────
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        String projectName = request.getParameter("projectName");
        String startDate   = request.getParameter("startDate");
        String objective   = request.getParameter("objective");
        String status      = request.getParameter("status");

        SpaceOperation op = new SpaceOperation();
        op.setProjectName(projectName);
        op.setStartDate(startDate);
        op.setObjective(objective);
        op.setStatus(status);

        if ("update".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            op.setId(id);
            dao.updateOperation(op);
        } else {
            // Default: add new record
            dao.addOperation(op);
        }

        response.sendRedirect(request.getContextPath() + "/operations");
    }
}
