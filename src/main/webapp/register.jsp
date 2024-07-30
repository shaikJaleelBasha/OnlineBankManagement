<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/register.css">
</head>
<body>
<%@ include file="nav.jsp" %>
<div class="container">
        <h1>Bank Application Registration</h1>
        <form action="submit_registration" method="post">
            <fieldset>
                <legend>Personal Information</legend>
                <label for="first-name">First Name:</label>
                <input type="text" id="first-name" name="first-name" required>
                
                <label for="last-name">Last Name:</label>
                <input type="text" id="last-name" name="last-name" required>
                
                <label for="dob">Date of Birth:</label>
                <input type="date" id="dob" name="dob" required>
                
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>
                
                <label for="phone">Phone Number:</label>
                <input type="tel" id="phone" name="phone" required>
                
                <label for="address">Address:</label>
                <textarea id="address" name="address" required></textarea>
            </fieldset>
            
            <fieldset>
                <legend>Account Information</legend>
                <label for="account-type">Account Type:</label>
                <select id="account-type" name="account-type" required>
                    <option value="savings">Savings</option>
                    <option value="current">Current</option>
                    <option value="fixed-deposit">Fixed Deposit</option>
                </select>
                
                <label for="branch">Branch:</label>
                <input type="text" id="branch" name="branch" required>
                
                <label for="account-number">Account Number:</label>
                <input type="text" id="account-number" name="account-number" required>
                
                <label for="ifsc">IFSC Code:</label>
                <input type="text" id="ifsc" name="ifsc" required>
                
                <label for="nominee">Nominee Name:</label>
                <input type="text" id="nominee" name="nominee" required>
                
                <label for="amount"><i class="zmdi zmdi-money"></i>Amount:</label> 
                <input type="text" name="amount" id="amount"  required />
                <label for="pin">PIN:</label>
                <input type="password" id="pin" name="pin" required>
            </fieldset>
            
            <button type="submit">Register</button> 
        </form>
    </div>
</body>
</html>