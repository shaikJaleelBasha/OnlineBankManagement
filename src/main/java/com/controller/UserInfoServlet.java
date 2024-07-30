package com.controller;

import com.model.RegistrationForm;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/userinfo")
public class UserInfoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String pin = request.getParameter("pin");
        RegistrationForm form = null;

        try {
            form = getUserInfo(email, pin);

            if (form != null) {
                request.setAttribute("form", form);
                request.getRequestDispatcher("userinfo.jsp").forward(request, response);
            } else {
                request.setAttribute("status", "invalid");
                request.getRequestDispatcher("userinfo_form.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("Error processing user info", e);
        }
    }

    private RegistrationForm getUserInfo(String email, String pin) throws SQLException {
        String jdbcURL = "jdbc:mysql://localhost:3306/bankmanagement";
        String jdbcUsername = "root";
        String jdbcPassword = "Jaleel@123";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            String sql = "SELECT * FROM registrations WHERE email = ? AND pin = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, pin);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                RegistrationForm form = new RegistrationForm();
                form.setFirstName(resultSet.getString("first_name"));
                form.setLastName(resultSet.getString("last_name"));
                form.setEmail(resultSet.getString("email"));
                form.setPhone(resultSet.getString("phone"));
                form.setDob(resultSet.getString("dob"));
                form.setAddress(resultSet.getString("address"));
                form.setAccountType(resultSet.getString("account_type"));
                form.setBranch(resultSet.getString("branch"));
                form.setAccountNumber(resultSet.getString("account_number"));
                form.setIfsc(resultSet.getString("ifsc"));
                form.setNominee(resultSet.getString("nominee"));
                form.setAmount(resultSet.getBigDecimal("amount"));
                // Not including the PIN in the response
                return form;
            }
            return null;
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found", e);
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
