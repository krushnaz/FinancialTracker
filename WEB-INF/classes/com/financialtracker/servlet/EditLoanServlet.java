package com.financialtracker.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.financialtracker.dao.LoanDao;
import com.financialtracker.entities.*;
import com.financialtracker.util.ConnectionProvider;

@WebServlet("/EditLoanServlet")
public class EditLoanServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditLoanServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Retrieve data from the form
            int loanId = Integer.parseInt(request.getParameter("debtId"));
            String date = request.getParameter("date");
            System.out.println("loan date"+date);
            double amount = Double.parseDouble(request.getParameter("amount"));
            double interestRate = Double.parseDouble(request.getParameter("interestRate"));
            int category = Integer.parseInt(request.getParameter("category"));
            String paymentSchedule = request.getParameter("paymentschedule");
            String creditor = request.getParameter("creditor");
            String paymentMode = request.getParameter("incomeMode");
            String description = request.getParameter("description");

            // Create a Loan object with the updated details
            Loan updatedLoan = new Loan(loanId, date, amount, interestRate, category, paymentSchedule, creditor, paymentMode, description);

            // Update the loan in the database
            LoanDao loanDao = new LoanDao(ConnectionProvider.getConnection()); // Create your LoanDao instance
            boolean success = loanDao.updateLoan(updatedLoan);

            if (success) {
            	System.out.println("data Updated sucessfully");
            	  Message msg = new Message("success", "Loan data Updated sucessfully.", "alert-success");
              	HttpSession session1 = request.getSession();
              	session1.setAttribute("msg", msg);
                // Successfully updated loan
                response.sendRedirect("addloan.jsp"); // Redirect to a success page
            } else {
                // Failed to update loan
                response.sendRedirect("error.jsp"); // Redirect to an error page
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp"); // Redirect to an error page
        }
    }
}
