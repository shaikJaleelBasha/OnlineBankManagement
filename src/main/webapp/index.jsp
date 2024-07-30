<%@ page language="java" contentType="text/html; charset=UTF-8"
    import="java.util.*, java.sql.*"
    pageEncoding="UTF-8"%>


<%@ page import="com.model.RegistrationForm" %>

<%
if (session.getAttribute("name") == null) {
    response.sendRedirect("login.jsp");
    return;
}
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome to Ula Bank</title>
    <link rel="stylesheet" href="css/bank.css">
</head>
<body>
   <%@ include file="nav.jsp" %>
    <div class="profile-section">
        <div class="profile">
            <a href="userinfo_form.jsp"><img src="images/user.png" alt="Profile"></a>
        </div>
        <div class="username">
            <%
        String username = (String) session.getAttribute("name");
        if (username != null) {
    %>
        <h1>Hello, <%= username %>!</h1>
    <%
        } else {
    %>
        <p>Hello, Guest!</p>
    <%
        }
    %>
        </div>
    </div>
    <div class="content">
        <div class="buttons">
            <div class="button-container">
                <img src="images/payment.png" alt="Payment">
                <button onclick="redirectTo('payment.jsp')">Payment</button>
            </div>
            <div class="button-container">
                <img src="images/checkBal.jpg" alt="Check Balance">
                <button onclick="redirectTo('checkBalance.jsp')">Check Balance</button>
            </div>
            <div class="button-container">
                <img src="images/transaction.jpg" alt="Transaction History">
                <button onclick="redirectTo('transactionHistory.jsp')">Transaction History</button>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        function redirectTo(page) {
            window.location.href = page;
        }
    </script>
</body>
</html>
