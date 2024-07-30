<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Navigation</title>
    <link rel="stylesheet" href="css/nav.css">
</head>
<body>
    <div class="header">
        <h1>Welcome to Lelo Bank</h1>
    </div>
    <div class="nav">
        <a id="home" href="index.jsp">Home</a>
         <!-- Added Contact link -->
        <div class="right">
         <a id="contact" href="contact.jsp">Contact</a>
         <a id="feedback" href="feedback.jsp">Feed Back</a>
            <a href="register.jsp">Register</a>
            <c:choose>
                <c:when test="${not empty sessionScope.firstName}">
                    <a href="LogoutServlet">Logout</a>
                </c:when>
                <c:otherwise>
                    <a href="login.jsp">Logout</a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</body>
</html>
