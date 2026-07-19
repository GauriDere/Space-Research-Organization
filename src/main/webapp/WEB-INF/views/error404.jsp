<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>SRO - 404 Not Found</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/index.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
</head>
<body>
<div class="page-shell" style="justify-content:center; align-items:center; min-height:100vh;">
    <div class="panel auth-card" style="text-align:center; padding:60px 40px;">
        <i class="fa-solid fa-satellite-dish" style="font-size:4rem; color:#8cb3ff; margin-bottom:20px;"></i>
        <h1 style="font-size:5rem; color:#ff2b54; margin:0;">404</h1>
        <h2 style="color:#8cb3ff;">Signal Lost</h2>
        <p style="color:rgba(255,255,255,.7);">The page you're looking for does not exist in our star charts.</p>
        <a href="${pageContext.request.contextPath}/index.html" class="btn-primary" style="margin-top:20px; display:inline-block;">
            <i class="fa-solid fa-house"></i> Return to Base
        </a>
    </div>
</div>
</body>
</html>
