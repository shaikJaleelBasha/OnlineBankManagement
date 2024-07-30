package com.controller;

import com.model.Transactions;
import javax.servlet.RequestDispatcher;
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
import java.util.ArrayList;
import java.util.List;


public class TransactionHistoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accountNumber = request.getParameter("accountNumber");
        String pin = request.getParameter("pin");

        List<Transactions> transactions = new ArrayList<>();
        String dbuser = "root";
        String dbpass = "Jaleel@123";
        String dburl = "jdbc:mysql://localhost:3306/bankmanagement?useSSL=false";

        try (Connection con = DriverManager.getConnection(dburl, dbuser, dbpass)) {
            // Validate the user's account number and pin
            String pinQuery = "SELECT pin FROM registrations WHERE account_number = ?";
            try (PreparedStatement pinPst = con.prepareStatement(pinQuery)) {
                pinPst.setString(1, accountNumber);
                try (ResultSet pinRs = pinPst.executeQuery()) {
                    if (pinRs.next() && pin.equals(pinRs.getString("pin"))) {
                        // Fetch the transactions where the user is the sender
                        String query = "SELECT date, sender_account, receiver_account, amount FROM transactions WHERE sender_account = ?";
                        try (PreparedStatement pst = con.prepareStatement(query)) {
                            pst.setString(1, accountNumber);
                            try (ResultSet rs = pst.executeQuery()) {
                                while (rs.next()) {
                                    Transactions transaction = new Transactions();
                                    transaction.setDate(rs.getDate("date"));
                                    transaction.setSenderAccount(rs.getString("sender_account"));
                                    transaction.setReceiverAccount(rs.getString("receiver_account"));
                                    transaction.setAmount(rs.getBigDecimal("amount"));
                                    transactions.add(transaction);
                                }
                            }
                        }
                    } else {
                        request.setAttribute("status", "invalid_pin");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("status", "error_sql_exception");
        }

        if (transactions.isEmpty() && !"invalid_pin".equals(request.getAttribute("status"))) {
            request.setAttribute("status", "no_transactions_found");
        }

        request.setAttribute("transactions", transactions);
        RequestDispatcher dispatcher = request.getRequestDispatcher("transactionHistory.jsp");
        dispatcher.forward(request, response);
    }
}
