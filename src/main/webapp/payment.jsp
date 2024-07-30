<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Transaction</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/transaction.css">
</head>
<body>
<%@ include file="nav.jsp" %>
    <div class="container">
        <h2>Make a Transaction</h2>
        <form action="${pageContext.request.contextPath}/transaction" method="post">
            <div class="form-group">
                <label for="sender_phone">Sender Phone Number:</label>
                <input type="text" id="sender_phone" name="sender_phone" required>
            </div>
            <div class="form-group">
                <label for="receiver_account">Receiver Account Number:</label>
                <input type="text" id="receiver_account" name="receiver_account" required>
            </div>
            <div class="form-group">
                <label for="amount">Amount:</label>
                <input type="number" id="amount" name="amount" required>
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
                    <p style="color:green;">Transaction successful!</p>
                </c:when>
                <c:when test="${status == 'insufficient'}">
                    <p style="color:red;">Insufficient balance.</p>
                </c:when>
                <c:when test="${status == 'sender_not_found'}">
                    <p style="color:red;">Sender not found.</p>
                </c:when>
                <c:when test="${status == 'invalid_pin'}">
                    <p style="color:red;">Invalid PIN.</p>
                </c:when>
                <c:otherwise>
                    <p style="color:red;">Transaction failed. Please try again.</p>
                </c:otherwise>
            </c:choose>
        </c:if>
    </div>
</body>
</html>
