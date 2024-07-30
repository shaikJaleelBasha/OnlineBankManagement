
<%@ page import="com.model.RegistrationForm" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Successful</title>
    <!-- Link to the CSS file -->
    <link rel="stylesheet" href="css/registerSucess.css">
</head>
<body>
<%@ include file="nav.jsp" %>
    <div class="container">
        <h2>Registration Successful</h2>
        <p>Thank you for registering! Here are the details you entered:</p>
        
        <table>
            <tr>
                <th>First Name:</th>
                <td>${form.firstName}</td>
            </tr>
            <tr>
                <th>Last Name:</th>
                <td>${form.lastName}</td>
            </tr>
            <tr>
                <th>Email:</th>
                <td>${form.email}</td>
            </tr>
            <tr>
                <th>Phone:</th>
                <td>${form.phone}</td>
            </tr>
            <tr>
                <th>Date of Birth:</th>
                <td>${form.dob}</td>
            </tr>
            <tr>
                <th>Address:</th>
                <td>${form.address}</td>
            </tr>
            <tr>
                <th>Account Type:</th>
                <td>${form.accountType}</td>
            </tr>
            <tr>
                <th>Branch:</th>
                <td>${form.branch}</td>
            </tr>
            <tr>
                <th>Account Number:</th>
                <td>${form.accountNumber}</td>
            </tr>
            <tr>
                <th>IFSC:</th>
                <td>${form.ifsc}</td>
            </tr>
            <tr>
                <th>Nominee:</th>
                <td>${form.nominee}</td>
            </tr>
            <tr>
                <th>Amount:</th>
                <td>${form.amount}</td>
            </tr>
        </table>
    </div>
</body>
</html>
