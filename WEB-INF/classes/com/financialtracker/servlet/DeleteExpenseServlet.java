package com.financialtracker.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.financialtracker.dao.ExpenseDao;
import com.financialtracker.entities.Message;
import com.financialtracker.dao.*;
import com.financialtracker.util.*;

@WebServlet("/DeleteExpenseServlet")
public class DeleteExpenseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int expenseID = Integer.parseInt(request.getParameter("expenseID"));
            System.out.println(expenseID);

            // Delete the expense record based on the expenseID
            ExpenseDao expenseDao = new ExpenseDao(ConnectionProvider.getConnection());
            boolean deleted = expenseDao.deleteExpense(expenseID);

            if (deleted) {
                // Record deleted successfully
                // Redirect to a success page or perform any other actions
                Message msg = new Message("success", "Expense data deleted successfully", "alert-success");
                HttpSession session = request.getSession();
                session.setAttribute("msg", msg);
                response.sendRedirect("addexpense.jsp"); // Replace with the appropriate page
            } else {
                // Handle the case where deletion failed, e.g., show an error message
                Message msg = new Message("error", "Failed to delete Expense data", "alert-danger");
                HttpSession session = request.getSession();
                session.setAttribute("msg", msg);
                response.sendRedirect("addexpense.jsp"); // Replace with the appropriate page
            }
        } catch (NumberFormatException e) {
            // Handle invalid expenseID parameter (not an integer)
            Message msg = new Message("error", "Invalid Expense ID", "alert-danger");
            HttpSession session = request.getSession();
            session.setAttribute("msg", msg);
            response.sendRedirect("addexpense.jsp"); // Replace with the appropriate page
        }
    }
}
