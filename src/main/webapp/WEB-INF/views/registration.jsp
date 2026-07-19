<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--
  registration.jsp — JSP Registration Page (Assignment Point 3 & 7)
  Served by RegistrationServlet → /register
  Re-displays form with error messages on validation failure.
--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SRO - Cadet Registration (JSP)</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/index.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
    <style>
        .field-error { color: #ff7992; font-size: 0.82rem; margin-top: 4px; display: block; }
        .err-box     { color: #ff7992; font-weight: 600; margin-bottom: 12px; }
    </style>
</head>
<body>
<div class="page-shell">
    <aside class="sidebar">
        <div class="brand-block">
            <img class="brand-logo" src="${pageContext.request.contextPath}/images/spacelogo.png" alt="Orion logo">
            <div><h2>ORION</h2><p>Space Research</p></div>
        </div>
        <nav class="side-nav">
            <a href="${pageContext.request.contextPath}/index.html"><i class="fa-solid fa-house"></i>Home</a>
            <a href="${pageContext.request.contextPath}/spaceprojects.xml"><i class="fa-solid fa-satellite"></i>Missions</a>
            <a class="active" href="${pageContext.request.contextPath}/register"><i class="fa-solid fa-address-card"></i>Registration</a>
            <a href="${pageContext.request.contextPath}/internship.html"><i class="fa-solid fa-flask-vial"></i>Internship</a>
            <a href="${pageContext.request.contextPath}/operations"><i class="fa-solid fa-database"></i>CRUD</a>
            <a href="${pageContext.request.contextPath}/login"><i class="fa-solid fa-user"></i>Login</a>
        </nav>
    </aside>

    <main class="main-content">
        <nav class="top-nav">
            <ul class="top-links">
                <li><a href="${pageContext.request.contextPath}/index.html">Home</a></li>
                <li><a href="${pageContext.request.contextPath}/spaceprojects.xml">Missions</a></li>
                <li><a class="active" href="${pageContext.request.contextPath}/register">Registration</a></li>
                <li><a href="${pageContext.request.contextPath}/operations">Operations CRUD</a></li>
                <li><a href="${pageContext.request.contextPath}/login">Login</a></li>
            </ul>
        </nav>

        <section class="hero auth-wrap">
            <p class="hero-kicker">Application Registration</p>
            <h1>CADET <span>REGISTRATION</span></h1>

            <div class="panel auth-card">
                <%
                    // e. Re-display error messages (from RegistrationServlet)
                    String errors   = (String) request.getAttribute("errors");
                    String fullName = (String) request.getAttribute("fullName");
                    String mobile   = (String) request.getAttribute("mobile");
                    String email    = (String) request.getAttribute("email");
                    if (fullName == null) fullName = "";
                    if (mobile   == null) mobile   = "";
                    if (email    == null) email    = "";
                %>

                <% if (errors != null && !errors.isEmpty()) { %>
                    <div class="err-box"><%= errors %></div>
                <% } %>

                <form id="registrationForm" method="post"
                      action="${pageContext.request.contextPath}/register">

                    <div class="form-group">
                        <label for="fullName">Full Name</label>
                        <input type="text" id="fullName" name="fullName"
                               placeholder="Enter full name"
                               value="<%= fullName %>">
                    </div>

                    <div class="form-group">
                        <label for="mobile">Mobile Number</label>
                        <input type="text" id="mobile" name="mobile"
                               placeholder="10-digit mobile number"
                               value="<%= mobile %>">
                    </div>

                    <div class="form-group">
                        <label for="email">Email ID</label>
                        <input type="text" id="email" name="email"
                               placeholder="name@example.com"
                               value="<%= email %>">
                    </div>

                    <button class="btn-primary auth-submit" type="submit">Register</button>
                </form>
                <p class="auth-note" style="margin-top:12px;">
                    Already have an account? <a href="${pageContext.request.contextPath}/login">Login here</a>
                </p>
            </div>
        </section>
    </main>
</div>
</body>
</html>
