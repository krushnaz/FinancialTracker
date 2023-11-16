package com.financialtracker.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.financialtracker.dao.LoanDao; // Import the correct DAO class for loans
import com.financialtracker.entities.Loan; // Import the correct entity class for loans
import com.financialtracker.entities.Message;
import com.financialtracker.util.ConnectionProvider;

@WebServlet("/AddLoanServlet")
public class AddLoanServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AddLoanServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the data from the request parameters
        String date = request.getParameter("date");
        double amount = Double.parseDouble(request.getParameter("amount"));
        String creditor = request.getParameter("creditor"); // Assuming creditor is an input field for loans
        double interestRate = Double.parseDouble(request.getParameter("interestRate"));
        String paymentSchedule = request.getParameter("paymentschedule");
        String PaymentMode = request.getParameter("incomeMode");
        int categoryID = Integer.parseInt(request.getParameter("category"));
        String description = request.getParameter("description");
//        String debtName = request.getParameter("debtName"); // Add this line to get debtName

        // TODO: Get the UserID. You can obtain this information based on your application's authentication mechanism.
        HttpSession session = request.getSession();
        Integer userID = (Integer) session.getAttribute("UserID");

        if (userID == null) {
            // UserID is null, set an error message
            request.setAttribute("errorMessage", "Please log in again");

            // Forward the request to login.jsp
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return; // Stop further processing
        }

        Loan loan = new Loan(userID, creditor,date, amount,interestRate, paymentSchedule,description, categoryID,PaymentMode);
        LoanDao loanDao = new LoanDao(ConnectionProvider.getConnection());

        if (loanDao.addLoan(loan)) {
        	System.out.println("data inserted sucessfully");
      	  Message msg = new Message("success", "Loan data inserted sucessfully.", "alert-success");
        	HttpSession session1 = request.getSession();
        	session1.setAttribute("msg", msg);
            // Add a success message here
            response.sendRedirect("addloan.jsp");
        } else {
            System.out.println("Error occurred while inserting data");
            // Add an error message and redirect to an error page
            response.sendRedirect("error.jsp");
        }
    }
}
