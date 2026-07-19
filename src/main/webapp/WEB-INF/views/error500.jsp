<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>SRO - 500 System Error</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/index.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
</head>
<body>
<div class="page-shell" style="justify-content:center; align-items:center; min-height:100vh;">
    <div class="panel auth-card" style="text-align:center; padding:60px 40px;">
        <i class="fa-solid fa-triangle-exclamation" style="font-size:4rem; color:#f0c040; margin-bottom:20px;"></i>
        <h1 style="font-size:5rem; color:#ff7992; margin:0;">500</h1>
        <h2 style="color:#f0c040;">Mission Control Error</h2>
        <p style="color:rgba(255,255,255,.7);">A system error occurred. Our engineers are on it.</p>
        <% if (exception != null) { %>
            <p style="color:#ff7992; font-size:0.85rem; margin-top:10px;"><%= exception.getMessage() %></p>
        <% } %>
        <a href="${pageContext.request.contextPath}/index.html" class="btn-primary" style="margin-top:20px; display:inline-block;">
            <i class="fa-solid fa-house"></i> Return to Base
        </a>
    </div>
</div>
</body>
</html>
