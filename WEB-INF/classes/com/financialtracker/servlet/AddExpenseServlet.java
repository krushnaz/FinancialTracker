package com.financialtracker.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.financialtracker.dao.ExpenseDao;
import com.financialtracker.entities.Expense;
import com.financialtracker.entities.Message;
import com.financialtracker.util.ConnectionProvider;

@WebServlet("/AddExpenseServlet")
public class AddExpenseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AddExpenseServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the expense details from the form
        
    	String date = request.getParameter("date");
    	double amount = Double.parseDouble(request.getParameter("amount"));
    	int expenseCategoryID = Integer.parseInt(request.getParameter("category"));
    	   String source = request.getParameter("incomeMode");
        String description = request.getParameter("description");
//        boolean recurring = Boolean.parseBoolean(request.getParameter("recurring"));

        // Create an Expense object with the provided details
        HttpSession session = request.getSession();
        Integer UserID = (Integer) session.getAttribute("UserID");
        System.out.println("user id is from expense:"+UserID);
        if (UserID == null) {
            // UserID is null, set an error message
            request.setAttribute("errorMessage", "Please log in again.");

            // Forward the request to login.jsp
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return; // Stop further processing
        }
        
        Expense expense = new Expense(UserID, description, amount, date,source, expenseCategoryID);

        // Initialize the ExpenseDao with a database connection
        ExpenseDao expenseDao = new ExpenseDao(ConnectionProvider.getConnection());

        // Add the expense to the database
        boolean isExpenseAdded = expenseDao.addExpense(expense);

        if (isExpenseAdded) {
            // Expense added successfully, redirect to a success page
        	System.out.println("Expense data inserted sucessfully");
      	  Message msg = new Message("success", "Expense data inserted sucessfully.", "alert-success");
        	HttpSession session1 = request.getSession();
        	session1.setAttribute("msg", msg);
            response.sendRedirect("addexpense.jsp");
        } else {
            // Handle the case where the expense couldn't be added, possibly show an error message
            response.sendRedirect("error.jsp");
        }
    }
}
