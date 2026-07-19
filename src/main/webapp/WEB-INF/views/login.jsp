<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--
  login.jsp — JSP Login Page demonstrating JSP with server-side error handling (Assignment Point 7)
  Served via LoginServlet → GET /login
--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SRO - Mission Login (JSP)</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/index.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
    <script>
        // JS Validation before form submission (Assignment Point 2b)
        function validateLoginForm(event) {
            event.preventDefault();
            var username = document.getElementById("username").value.trim();
            var password = document.getElementById("password").value.trim();

            if (username === "") {
                alert("ACCESS DENIED: Name/Username field cannot be empty.");
                return false;
            }
            if (password === "") {
                alert("ACCESS DENIED: Password field cannot be empty.");
                return false;
            }
            if (password.length < 6) {
                alert("SECURITY ALERT: Password must be at least 6 characters long.");
                return false;
            }
            // All checks passed — submit
            document.getElementById("loginForm").submit();
        }

        // Prompt on page load (Assignment Point 2c)
        window.onload = function() {
            var codename = prompt("Authenticate Command: Enter your SRO Codename", "Guest");
            if (codename != null && codename.trim() !== "") {
                document.getElementById("welcome-msg").innerText = "Welcome back, Operative " + codename;
            }
        };
    </script>
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
            <a href="${pageContext.request.contextPath}/register"><i class="fa-solid fa-address-card"></i>Registration</a>
            <a href="${pageContext.request.contextPath}/internship.html"><i class="fa-solid fa-flask-vial"></i>Internship</a>
            <a href="${pageContext.request.contextPath}/operations"><i class="fa-solid fa-database"></i>CRUD</a>
            <a class="active" href="${pageContext.request.contextPath}/login"><i class="fa-solid fa-user"></i>Login</a>
        </nav>
    </aside>

    <main class="main-content">
        <nav class="top-nav">
            <ul class="top-links">
                <li><a href="${pageContext.request.contextPath}/index.html">Home</a></li>
                <li><a href="${pageContext.request.contextPath}/spaceprojects.xml">Missions</a></li>
                <li><a href="${pageContext.request.contextPath}/register">Registration</a></li>
                <li><a href="${pageContext.request.contextPath}/operations">Operations CRUD</a></li>
                <li><a class="active" href="${pageContext.request.contextPath}/login">Login</a></li>
            </ul>
        </nav>

        <section class="hero auth-wrap">
            <p class="hero-kicker">Secure Access</p>
            <h1>MISSION <span>LOGIN</span></h1>

            <div class="panel auth-card">
                <p id="welcome-msg" class="auth-note">Please authenticate</p>

                <%-- Show server-side error if returned from servlet --%>
                <% String error = (String) request.getAttribute("error");
                   if (error != null && !error.isEmpty()) { %>
                    <div class="auth-warning" style="margin-bottom:12px;"><%= error %></div>
                <% } %>

                <form id="loginForm" name="loginForm" method="post"
                      action="${pageContext.request.contextPath}/login"
                      onsubmit="validateLoginForm(event)">
                    <div class="form-group">
                        <label for="username">Name / Codename</label>
                        <input type="text" id="username" name="username"
                               placeholder="Enter your username"
                               value="<%= request.getParameter("username") != null ? request.getParameter("username") : "" %>">
                    </div>
                    <div class="form-group">
                        <label for="password">Security Key</label>
                        <input type="password" id="password" name="password"
                               placeholder="Enter your password">
                    </div>
                    <input type="submit" value="Launch Sequence (Login)" class="btn-primary auth-submit">
                </form>
                <p class="auth-note" style="margin-top:12px;">
                    Not registered? <a href="${pageContext.request.contextPath}/register">Register here</a>
                </p>
            </div>
        </section>
    </main>
</div>
</body>
</html>
