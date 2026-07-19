<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--
  welcome_registration.jsp — Congratulations / Welcome page (Assignment Point 3f)
  Shown after RegistrationServlet successfully saves a cadet registration.
--%>
<%
    String fullName = (String) request.getAttribute("fullName");
    if (fullName == null) fullName = "Cadet";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SRO - Welcome, <%= fullName %></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/index.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
    <style>
        .congrats-card { text-align:center; padding:40px 30px; }
        .congrats-icon { font-size:3.5rem; color:#5de88a; margin-bottom:18px; }
        .congrats-card h2 { font-size:1.9rem; color:#5de88a; margin-bottom:10px; }
        .congrats-card p  { color:rgba(255,255,255,.78); margin-bottom:10px; }
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
        <section class="hero auth-wrap">
            <p class="hero-kicker">Registration Successful</p>
            <h1>WELCOME <span>ABOARD</span></h1>

            <div class="panel auth-card congrats-card">
                <div class="congrats-icon">
                    <i class="fa-solid fa-circle-check"></i>
                </div>
                <h2>Congratulations, <%= fullName %>!</h2>
                <p>Your registration with the <strong>ORION Space Research Organization</strong> is complete.</p>
                <p>You are now part of our elite team of space explorers and researchers.</p>
                <p style="color:#8cb3ff; margin-top:16px;">
                    <i class="fa-solid fa-rocket"></i> &nbsp;Prepare for your first mission briefing.
                </p>

                <div style="display:flex; gap:12px; justify-content:center; flex-wrap:wrap; margin-top:28px;">
                    <a href="${pageContext.request.contextPath}/login" class="btn-primary">
                        <i class="fa-solid fa-user"></i> Login Now
                    </a>
                    <a href="${pageContext.request.contextPath}/index.html" class="btn-secondary">
                        <i class="fa-solid fa-house"></i> Go to Home
                    </a>
                </div>
            </div>
        </section>
    </main>
</div>
</body>
</html>
