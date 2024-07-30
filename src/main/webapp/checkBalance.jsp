<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Check Balance</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/checkBalance.css">
</head>
<body>
<%@ include file="nav.jsp" %>
    <div class="container">
        <h2>Check Your Balance</h2>
        <form action="${pageContext.request.contextPath}/checkBalance" method="post">
            <div class="form-group">
                <label for="phone">Phone Number:</label>
                <input type="text" id="phone" name="phone" required>
            </div>
            <div class="form-group">
                <label for="pin">PIN:</label>
                <input type="password" id="pin" name="pin" required>
            </div>
            <div class="form-group">
                <button type="submit">Submit</button>
            </div>
        </form>
        <c:if test="${not empty status}">
            <c:choose>
                <c:when test="${status == 'success'}">
                    <p style="color:green;">Balance check successful!</p>
                    <p>Phone Number: ${phone}</p>
                    <p>Account Number: ${accountNumber}</p>
                    <p>Balance: ${balance}</p>
                </c:when>
                <c:when test="${status == 'invalid_pin'}">
                    <p style="color:red;">Invalid PIN. Please try again.</p>
                </c:when>
                <c:when test="${status == 'account_not_found'}">
                    <p style="color:red;">Account not found. Please try again.</p>
                </c:when>
                <c:otherwise>
                    <p style="color:red;">An error occurred. Please try again.</p>
                </c:otherwise>
            </c:choose>
        </c:if>
    </div>
</body>
</html>
