package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class RegistrationLogin extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final int PASSWORD_MAX_LENGTH = 255;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        // Handle login requests
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username != null && password != null) {
            handleLogin(request, response, username, password);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        Connection con = null;
        PreparedStatement pst = null;
        RequestDispatcher dispatcher = null;
        HttpSession session = request.getSession();

        String dbuser = "root";
        String dbpass = "Jaleel@123";
        String dburl = "jdbc:mysql://localhost:3306/bankmanagement?useSSL=false";

        String uname = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("pass");
        String fullname = request.getParameter("Full_name");
        String date = request.getParameter("Created_date");
        String contact = request.getParameter("contact");

        boolean isValidInput = uname != null && !uname.isEmpty() &&
            email != null && !email.isEmpty() &&
            password != null && !password.isEmpty() &&
            fullname != null && !fullname.isEmpty() &&
            date != null && !date.isEmpty() &&
            contact != null && !contact.isEmpty();

        if (!isValidInput) {
            request.setAttribute("status", "fail");
            dispatcher = request.getRequestDispatcher("registerApp.jsp");
            dispatcher.forward(request, response);
            return;
        }

        if (password.length() > PASSWORD_MAX_LENGTH) {
            password = password.substring(0, PASSWORD_MAX_LENGTH);
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(dburl, dbuser, dbpass);
            pst = con.prepareStatement("INSERT INTO user (Username, Email_address, Password, Full_name, Created_date, contact) VALUES (?, ?, ?, ?, ?, ?)");

            pst.setString(1, uname);
            pst.setString(2, email);
            pst.setString(3, password);
            pst.setString(4, fullname);
            pst.setString(5, date);
            pst.setString(6, contact);

            int rowCount = pst.executeUpdate();

            if (rowCount > 0) {
                session.setAttribute("phone", contact); // Set phone number in session
                request.setAttribute("status", "success");
                dispatcher = request.getRequestDispatcher("login.jsp");
            } else {
                request.setAttribute("status", "fail");
                dispatcher = request.getRequestDispatcher("registerApp.jsp");
            }
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("status", "error");
            dispatcher = request.getRequestDispatcher("registerApp.jsp");
            dispatcher.forward(request, response);
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response, String username, String password)
            throws ServletException, IOException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        HttpSession session = request.getSession();
        RequestDispatcher dispatcher = null;

        String dbuser = "root";
        String dbpass = "Jaleel@123";
        String dburl = "jdbc:mysql://localhost:3306/bankmanagement?useSSL=false";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(dburl, dbuser, dbpass);
            pst = con.prepareStatement("SELECT * FROM user WHERE Username = ? AND Password = ?");

            pst.setString(1, username);
            pst.setString(2, password);

            rs = pst.executeQuery();

            if (rs.next()) {
                session.setAttribute("phone", rs.getString("contact")); // Set phone number in session
                session.setAttribute("username", rs.getString("Username"));
                dispatcher = request.getRequestDispatcher("index.jsp");
            } else {
                request.setAttribute("status", "invalid");
                dispatcher = request.getRequestDispatcher("login.jsp");
            }
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("status", "error");
            dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
