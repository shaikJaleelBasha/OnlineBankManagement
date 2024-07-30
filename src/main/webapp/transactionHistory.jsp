<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Transaction History</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/transactionHistory.css">
</head>
<body>
<%@ include file="nav.jsp" %>
    <div class="container">
        <h2>Transaction History</h2>
        <form action="${pageContext.request.contextPath}/transactionHistory" method="post">
            <div class="form-group">
                <label for="accountNumber">Account Number:</label>
                <input type="text" id="accountNumber" name="accountNumber" required>
            </div>
            <div class="form-group">
                <label for="pin">PIN:</label>
                <input type="password" id="pin" name="pin" required>
            </div>
            <div class="form-group">
                <button type="submit">Submit</button>
            </div>
        </form>
        <c:if test="${not empty transactions}">
            <table>
                <thead>
                    <tr>
                        <th>Date</th>
                        <th>Sender Account</th>
                        <th>Receiver Account</th>
                        <th>Amount</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="transaction" items="${transactions}">
                        <tr>
                            <td><c:out value="${transaction.date}"/></td>
                            <td><c:out value="${transaction.senderAccount}"/></td>
                            <td><c:out value="${transaction.receiverAccount}"/></td>
                            <td><c:out value="${transaction.amount}"/></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty transactions}">
            <p>No transactions found.</p>
        </c:if>
        <c:if test="${status == 'error_sql_exception'}">
            <p style="color:red;">An error occurred while retrieving transaction history. Please try again later.</p>
        </c:if>
        <c:if test="${status == 'invalid_pin'}">
            <p style="color:red;">Invalid PIN. Please try again.</p>
        </c:if>
    </div>
</body>
</html>
