<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Info Validation</title>
    <link rel="stylesheet" href="css/userinfo.css">
</head>
<body>
<%@ include file="nav.jsp" %>
    <div class="container">
        <h2>Enter Your Email and PIN to View Information</h2>
        <form action="${pageContext.request.contextPath}/userinfo" method="post">
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>
            </div>
            <div class="form-group">
                <label for="pin">PIN:</label>
                <input type="password" id="pin" name="pin" required>
            </div>
            <div class="form-group">
                <button type="submit">Submit</button>
            </div>
        </form>
        <c:if test="${not empty status and status == 'invalid'}">
            <p style="color:red;">Invalid email or PIN. Please try again.</p>
        </c:if>
    </div>
</body>
</html>
