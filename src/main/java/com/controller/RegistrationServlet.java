package com.controller;

import com.model.RegistrationForm;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class RegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        RegistrationForm form = new RegistrationForm();
        form.setFirstName(request.getParameter("first-name"));
        form.setLastName(request.getParameter("last-name"));
        form.setEmail(request.getParameter("email"));
        form.setPhone(request.getParameter("phone"));
        
        try {
            form.setDob(request.getParameter("dob"));
        } catch (Exception e) {
            throw new ServletException("Invalid date format", e);
        }
        
        form.setAddress(request.getParameter("address"));
        form.setAccountType(request.getParameter("account-type"));
        form.setBranch(request.getParameter("branch"));
        form.setAccountNumber(request.getParameter("account-number"));
        form.setIfsc(request.getParameter("ifsc"));
        form.setNominee(request.getParameter("nominee"));
        form.setAmount(new BigDecimal(request.getParameter("amount")));
        form.setPin(request.getParameter("pin"));

        HttpSession session = request.getSession();
        session.setAttribute("phone", form.getPhone());
        session.setAttribute("accountNumber", form.getAccountNumber());

        try {
            if (isUserExists(form)) {
                request.setAttribute("form", form);
                request.setAttribute("status", "user_exists");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } else {
                saveRegistrationForm(form);

                // Set session attributes
                session.setAttribute("firstName", form.getFirstName());
                session.setAttribute("lastName", form.getLastName());
                session.setAttribute("email", form.getEmail());
                session.setAttribute("dob", form.getDob());
                session.setAttribute("address", form.getAddress());
                session.setAttribute("accountType", form.getAccountType());
                session.setAttribute("branch", form.getBranch());
                session.setAttribute("ifsc", form.getIfsc());
                session.setAttribute("nominee", form.getNominee());
                session.setAttribute("amount", form.getAmount());

                request.setAttribute("form", form);
                request.setAttribute("status", "registration_success");
                request.getRequestDispatcher("registration_success.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("Error processing registration form", e);
        }
    }

    private boolean isUserExists(RegistrationForm form) throws SQLException {
        String jdbcURL = "jdbc:mysql://localhost:3306/bankmanagement";
        String jdbcUsername = "root";
        String jdbcPassword = "Jaleel@123";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            String sql = "SELECT COUNT(*) FROM registrations WHERE email = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, form.getEmail());
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
            return false;
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

    private void saveRegistrationForm(RegistrationForm form) throws SQLException {
        String jdbcURL = "jdbc:mysql://localhost:3306/bankmanagement";
        String jdbcUsername = "root";
        String jdbcPassword = "Jaleel@123";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            String sql = "INSERT INTO registrations (first_name, last_name, email, phone, dob, address, account_type, branch, account_number, ifsc, nominee, amount, pin) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, form.getFirstName());
            statement.setString(2, form.getLastName());
            statement.setString(3, form.getEmail());
            statement.setString(4, form.getPhone());
            statement.setDate(5, Date.valueOf(form.getDob())); // Convert string to SQL date
            statement.setString(6, form.getAddress());
            statement.setString(7, form.getAccountType());
            statement.setString(8, form.getBranch());
            statement.setString(9, form.getAccountNumber());
            statement.setString(10, form.getIfsc());
            statement.setString(11, form.getNominee());
            statement.setBigDecimal(12, form.getAmount());
            statement.setString(13, form.getPin());
            statement.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found", e);
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
