package com.financialtracker.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.financialtracker.dao.LoanDao;
import com.financialtracker.entities.Message;
import com.financialtracker.util.*;

@WebServlet("/DeleteLoanServlet")
public class DeleteLoanServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int loanID = Integer.parseInt(request.getParameter("loanID"));
            System.out.println(loanID);

            // Delete the loan record based on the loanID
            LoanDao loanDao = new LoanDao(ConnectionProvider.getConnection());
            boolean deleted = loanDao.deleteLoan(loanID);

            if (deleted) {
                // Record deleted successfully
                // Redirect to a success page or perform any other actions
                Message msg = new Message("success", "Loan data deleted successfully", "alert-success");
                HttpSession session = request.getSession();
                session.setAttribute("msg", msg);
                response.sendRedirect("addloan.jsp"); // Replace with the appropriate page
            } else {
                // Handle the case where deletion failed, e.g., show an error message
            	  Message msg = new Message("error", "Failed to delete Investment data", "alert-danger");
                  HttpSession session = request.getSession();
                  session.setAttribute("msg", msg);
                response.sendRedirect("addloan.jsp"); // Replace with the appropriate page
            }
        } catch (NumberFormatException e) {
            // Handle invalid loanID parameter (not an integer)
            Message msg = new Message("error", "Invalid Loan ID", "alert-danger");
            HttpSession session = request.getSession();
            session.setAttribute("msg", msg);
            response.sendRedirect("addloan.jsp"); // Replace with the appropriate page
        }
    }
}
