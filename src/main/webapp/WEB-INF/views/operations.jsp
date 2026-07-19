<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.sro.model.SpaceOperation, java.util.List" %>
<%--
  operations.jsp — JSP page demonstrating Servlet + JSP integration (Assignment Point 7)
  Displays the CRUD table and add/edit form for Space Operations.
  Served by SpaceOperationsServlet → forwarded here.
--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SRO - Operations CRUD (JSP)</title>
    <%-- Reuse existing CSS --%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/index.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
    <style>
        .mini-table { width:100%; border-collapse:collapse; }
        .mini-table th, .mini-table td { border:1px solid rgba(65,98,188,.45); padding:8px; }
        .mini-table th { color:#8cb3ff; text-transform:uppercase; font-size:.85rem; }
        .err-msg  { color:#ff7992; font-weight:600; margin-bottom:10px; }
        .succ-msg { color:#8cb3ff; font-weight:600; margin-bottom:10px; }
        .tag-planned    { color:#f0c040; }
        .tag-inprogress { color:#4fcee0; }
        .tag-completed  { color:#5de88a; }
    </style>
</head>
<body>
<div class="page-shell">
    <%-- Sidebar --%>
    <aside class="sidebar">
        <div class="brand-block">
            <img class="brand-logo" src="${pageContext.request.contextPath}/images/spacelogo.png" alt="Orion logo">
            <div><h2>ORION</h2><p>Space Research</p></div>
        </div>
        <nav class="side-nav">
            <a href="${pageContext.request.contextPath}/index.html"><i class="fa-solid fa-house"></i>Home</a>
            <a href="${pageContext.request.contextPath}/spaceprojects.xml"><i class="fa-solid fa-satellite"></i>Missions</a>
            <a href="${pageContext.request.contextPath}/register"><i class="fa-solid fa-address-card"></i>Registration</a>
            <a href="${pageContext.request.contextPath}/internship.html"><i class="fa-solid fa-flask-vial"></i>Internship</a>
            <a class="active" href="${pageContext.request.contextPath}/operations"><i class="fa-solid fa-database"></i>CRUD</a>
            <a href="${pageContext.request.contextPath}/login"><i class="fa-solid fa-user"></i>Login</a>
        </nav>
    </aside>

    <main class="main-content">
        <%-- Top Nav --%>
        <nav class="top-nav">
            <ul class="top-links">
                <li><a href="${pageContext.request.contextPath}/index.html">Home</a></li>
                <li><a href="${pageContext.request.contextPath}/spaceprojects.xml">Missions</a></li>
                <li><a href="${pageContext.request.contextPath}/register">Registration</a></li>
                <li><a href="${pageContext.request.contextPath}/internship.html">Internship</a></li>
                <li><a class="active" href="${pageContext.request.contextPath}/operations">Operations CRUD</a></li>
            </ul>
        </nav>

        <section class="hero auth-wrap">
            <p class="hero-kicker">Dynamic Operations — JSP + MySQL</p>
            <h1>SPACE <span>CRUD</span> MODULE</h1>

            <%-- ── Add / Edit Form ─────────────────────────────────── --%>
            <div class="panel">
                <%
                    SpaceOperation editOp = (SpaceOperation) request.getAttribute("editOp");
                    String formAction = (editOp != null) ? "update" : "add";
                %>
                <h3><%= (editOp != null) ? "Edit Operation" : "Add New Operation" %></h3>

                <form method="post" action="${pageContext.request.contextPath}/operations">
                    <input type="hidden" name="action" value="<%= formAction %>">
                    <% if (editOp != null) { %>
                        <input type="hidden" name="id" value="<%= editOp.getId() %>">
                    <% } %>

                    <div class="form-group">
                        <label>Project Name</label>
                        <input type="text" name="projectName" required
                               value="<%= editOp != null ? editOp.getProjectName() : "" %>">
                    </div>
                    <div class="form-group">
                        <label>Start Date</label>
                        <input type="date" name="startDate" required
                               value="<%= editOp != null ? editOp.getStartDate() : "" %>">
                    </div>
                    <div class="form-group">
                        <label>Objective</label>
                        <textarea name="objective" rows="3"><%= editOp != null ? editOp.getObjective() : "" %></textarea>
                    </div>
                    <div class="form-group">
                        <label>Status</label>
                        <select name="status">
                            <option value="">Select</option>
                            <option value="Planned"     <%= (editOp != null && "Planned".equals(editOp.getStatus()))     ? "selected" : "" %>>Planned</option>
                            <option value="In Progress" <%= (editOp != null && "In Progress".equals(editOp.getStatus())) ? "selected" : "" %>>In Progress</option>
                            <option value="Completed"   <%= (editOp != null && "Completed".equals(editOp.getStatus()))   ? "selected" : "" %>>Completed</option>
                        </select>
                    </div>
                    <button type="submit" class="btn-primary auth-submit">
                        <%= (editOp != null) ? "Update Operation" : "Save Operation" %>
                    </button>
                    <% if (editOp != null) { %>
                        <a href="${pageContext.request.contextPath}/operations" class="btn-secondary auth-submit" style="margin-left:10px;">Cancel</a>
                    <% } %>
                </form>
            </div>

            <%-- ── Operations Table ────────────────────────────────── --%>
            <div class="panel" style="margin-top:12px;">
                <h3>All Space Operations</h3>
                <%
                    List<SpaceOperation> ops = (List<SpaceOperation>) request.getAttribute("operations");
                    if (ops == null || ops.isEmpty()) {
                %>
                    <p class="auth-note">No operations found. Add one above.</p>
                <%
                    } else {
                %>
                <table class="mini-table">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Project</th>
                            <th>Start Date</th>
                            <th>Objective</th>
                            <th>Status</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                    <% for (SpaceOperation op : ops) {
                           String tagClass = "In Progress".equals(op.getStatus()) ? "tag-inprogress" :
                                            "Completed".equals(op.getStatus())    ? "tag-completed"  : "tag-planned";
                    %>
                        <tr>
                            <td><%= op.getId() %></td>
                            <td><%= op.getProjectName() %></td>
                            <td><%= op.getStartDate() %></td>
                            <td><%= op.getObjective() %></td>
                            <td class="<%= tagClass %>"><%= op.getStatus() %></td>
                            <td>
                                <a href="${pageContext.request.contextPath}/operations?action=edit&id=<%= op.getId() %>"
                                   class="btn-secondary">Edit</a>
                                &nbsp;
                                <a href="${pageContext.request.contextPath}/operations?action=delete&id=<%= op.getId() %>"
                                   class="btn-primary"
                                   onclick="return confirm('Delete this operation?')">Delete</a>
                            </td>
                        </tr>
                    <% } %>
                    </tbody>
                </table>
                <% } %>
            </div>
        </section>
    </main>
</div>
</body>
</html>
