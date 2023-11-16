package com.financialtracker.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.financialtracker.dao.*;
import com.financialtracker.entities.*;
import com.financialtracker.util.ConnectionProvider;
/**
 * Servlet implementation class EditIncomeServlet
 */
@WebServlet("/EditExpenseServlet")
public class EditExpenseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String expenseId = request.getParameter("expenseId");
        String date = request.getParameter("date");
        String amount = request.getParameter("amount");
        String categoryId = request.getParameter("category");
        String incomeMode = request.getParameter("incomeMode");
        String description = request.getParameter("description");

        // Create an Income object with the updated values
        Expense updateExpense = new Expense();
        updateExpense.setExpenseID(Integer.parseInt(expenseId));
        updateExpense.setDate(date);
        updateExpense.setAmount(Double.parseDouble(amount));
        updateExpense.setExpenseCategoryID(Integer.parseInt(categoryId));
        updateExpense.setPaymentMode(incomeMode);
        updateExpense.setDescription(description);

        // Call the DAO method to update the income
        ExpenseDao expenseDao = new ExpenseDao(ConnectionProvider.getConnection());
        expenseDao.updateExpense(updateExpense);
        
        
        boolean isExprenseUpdated = expenseDao.updateExpense(updateExpense);

        if (isExprenseUpdated) {
            // EMI added successfully, redirect to a success page
            Message msg = new Message("success", "Expense data Updated successfully.", "alert-success");
            HttpSession session1 = request.getSession();
            session1.setAttribute("msg", msg);
            response.sendRedirect("addexpense.jsp"); // Replace with your success page
        } else {
            // Handle the case where the EMI couldn't be added, possibly show an error message
            response.sendRedirect("error.jsp");
        }

    }
}
