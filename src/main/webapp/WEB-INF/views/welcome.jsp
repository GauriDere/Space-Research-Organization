<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--
  welcome.jsp — JSP Welcome / Dashboard page after successful login (Assignment Point 7)
  Shown after LoginServlet successfully authenticates the user.
  Session variable 'loggedUser' contains the username.
--%>
<%
    // If no session / not logged in, redirect to login
    String loggedUser = (String) session.getAttribute("loggedUser");
    if (loggedUser == null) {
        response.sendRedirect(request.getContextPath() + "/login");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SRO - Welcome, <%= loggedUser %></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/index.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
    <style>
        .welcome-card { text-align:center; padding: 40px 30px; }
        .welcome-card h2 { font-size: 2rem; color: #8cb3ff; margin-bottom: 10px; }
        .welcome-card p  { color: rgba(255,255,255,.75); margin-bottom: 20px; }
        .welcome-badge   {
            display: inline-block; padding: 8px 24px;
            border: 1px solid rgba(65,98,188,.5); border-radius: 20px;
            color: #8cb3ff; margin-bottom: 24px; font-size: 0.9rem;
        }
        .logout-btn { margin-top: 20px; }
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
            <a href="${pageContext.request.contextPath}/register"><i class="fa-solid fa-address-card"></i>Registration</a>
            <a href="${pageContext.request.contextPath}/internship.html"><i class="fa-solid fa-flask-vial"></i>Internship</a>
            <a href="${pageContext.request.contextPath}/operations"><i class="fa-solid fa-database"></i>CRUD</a>
            <a class="active" href="${pageContext.request.contextPath}/welcome"><i class="fa-solid fa-user"></i>Dashboard</a>
        </nav>
    </aside>

    <main class="main-content">
        <nav class="top-nav">
            <ul class="top-links">
                <li><a href="${pageContext.request.contextPath}/index.html">Home</a></li>
                <li><a href="${pageContext.request.contextPath}/spaceprojects.xml">Missions</a></li>
                <li><a href="${pageContext.request.contextPath}/operations">Operations CRUD</a></li>
                <li><a class="active" href="${pageContext.request.contextPath}/welcome">Dashboard</a></li>
            </ul>
        </nav>

        <section class="hero auth-wrap">
            <p class="hero-kicker">Access Granted</p>
            <h1>OPERATIVE <span>DASHBOARD</span></h1>

            <div class="panel auth-card welcome-card">
                <i class="fa-solid fa-rocket" style="font-size:3rem; color:#8cb3ff; margin-bottom:18px;"></i>
                <h2>Welcome, <%= loggedUser %>!</h2>
                <span class="welcome-badge"><i class="fa-solid fa-shield-halved"></i> Clearance Level: Operative</span>
                <p>You have successfully authenticated with the ORION Space Research Organization system.</p>
                <p>Use the navigation to access Missions, CRUD Operations, and Research modules.</p>

                <div style="display:flex; gap:12px; justify-content:center; flex-wrap:wrap; margin-top:20px;">
                    <a href="${pageContext.request.contextPath}/operations" class="btn-primary">
                        <i class="fa-solid fa-database"></i> Operations Console
                    </a>
                    <a href="${pageContext.request.contextPath}/spaceprojects.xml" class="btn-secondary">
                        <i class="fa-solid fa-satellite"></i> View Missions
                    </a>
                </div>

                <%-- Logout button — invalidates session --%>
                <form method="post" action="${pageContext.request.contextPath}/logout" class="logout-btn">
                    <button type="submit" class="btn-secondary auth-submit">
                        <i class="fa-solid fa-right-from-bracket"></i> Logout
                    </button>
                </form>
            </div>
        </section>
    </main>
</div>
</body>
</html>
