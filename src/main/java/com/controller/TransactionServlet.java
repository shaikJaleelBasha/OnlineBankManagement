package com.controller;

import java.io.IOException;
import java.math.BigDecimal;
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

public class TransactionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String senderPhone = request.getParameter("sender_phone");
        String senderPin = request.getParameter("pin");
        String receiverAccountNumber = request.getParameter("receiver_account");
        BigDecimal amount = new BigDecimal(request.getParameter("amount"));

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

            // Check sender's balance and PIN
            pst = con.prepareStatement("SELECT account_number, amount, pin FROM registrations WHERE phone = ?");
            pst.setString(1, senderPhone);
            rs = pst.executeQuery();

            if (rs.next()) {
                String senderAccountNumber = rs.getString("account_number");
                BigDecimal senderBalance = rs.getBigDecimal("amount");
                String storedPin = rs.getString("pin");

                if (!storedPin.equals(senderPin)) {
                    request.setAttribute("status", "invalid_pin");
                } else if (senderBalance.compareTo(amount) >= 0) {
                    // Start transaction
                    con.setAutoCommit(false);

                    // Update sender's balance
                    pst = con.prepareStatement("UPDATE registrations SET amount = amount - ? WHERE phone = ?");
                    pst.setBigDecimal(1, amount);
                    pst.setString(2, senderPhone);
                    pst.executeUpdate();

                    // Update receiver's balance
                    pst = con.prepareStatement("UPDATE registrations SET amount = amount + ? WHERE account_number = ?");
                    pst.setBigDecimal(1, amount);
                    pst.setString(2, receiverAccountNumber);
                    pst.executeUpdate();

                    // Record transaction
                    pst = con.prepareStatement("INSERT INTO transactions (sender_account, receiver_account, amount, date) VALUES (?, ?, ?, NOW())");
                    pst.setString(1, senderAccountNumber);
                    pst.setString(2, receiverAccountNumber);
                    pst.setBigDecimal(3, amount);
                    pst.executeUpdate();

                    // Commit transaction
                    con.commit();
                    request.setAttribute("status", "success");
                } else {
                    request.setAttribute("status", "insufficient");
                }
            } else {
                request.setAttribute("status", "sender_not_found");
            }

            dispatcher = request.getRequestDispatcher("payment.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("status", "error");
            dispatcher = request.getRequestDispatcher("payment.jsp");
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
