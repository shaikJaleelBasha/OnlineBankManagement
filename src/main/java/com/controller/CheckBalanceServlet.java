package com.controller;

import javax.servlet.RequestDispatcher;
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


public class CheckBalanceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String phone = request.getParameter("phone");
        String pin = request.getParameter("pin");

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        RequestDispatcher dispatcher = null;

        String dbuser = "root";
        String dbpass = "Jaleel@123";
        String dburl = "jdbc:mysql://localhost:3306/bankmanagement?useSSL=false";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(dburl, dbuser, dbpass);

            String query = "SELECT account_number, amount, pin FROM registrations WHERE phone = ?";
            pst = con.prepareStatement(query);
            pst.setString(1, phone);
            rs = pst.executeQuery();

            if (rs.next()) {
                String storedPin = rs.getString("pin");
                if (storedPin.equals(pin)) {
                    BigDecimal balance = rs.getBigDecimal("amount");
                    String accountNumber = rs.getString("account_number");

                    // Set session attributes
                    session.setAttribute("phone", phone);
                    session.setAttribute("accountNumber", accountNumber);

                    request.setAttribute("status", "success");
                    request.setAttribute("balance", balance);
                    request.setAttribute("phone", phone);
                    request.setAttribute("accountNumber", accountNumber);
                } else {
                    request.setAttribute("status", "invalid_pin");
                }
            } else {
                request.setAttribute("status", "account_not_found");
            }

            dispatcher = request.getRequestDispatcher("checkBalance.jsp");
            dispatcher.forward(request, response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            request.setAttribute("status", "error_class_not_found");
            dispatcher = request.getRequestDispatcher("checkBalance.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("status", "error_sql_exception");
            dispatcher = request.getRequestDispatcher("checkBalance.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("status", "error");
            dispatcher = request.getRequestDispatcher("checkBalance.jsp");
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
