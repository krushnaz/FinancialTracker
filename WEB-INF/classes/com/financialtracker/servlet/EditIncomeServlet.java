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
@WebServlet("/EditIncomeServlet")
public class EditIncomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String incomeId = request.getParameter("incomeId");
        String date = request.getParameter("date");
        String amount = request.getParameter("amount");
        String categoryId = request.getParameter("category");
        String incomeMode = request.getParameter("incomeMode");
        String description = request.getParameter("description");

        // Create an Income object with the updated values
        Income updatedIncome = new Income();
        updatedIncome.setIncomeID(Integer.parseInt(incomeId));
        updatedIncome.setDate(date);
        updatedIncome.setAmount(Double.parseDouble(amount));
        updatedIncome.setCategoryID(Integer.parseInt(categoryId));
        updatedIncome.setSource(incomeMode);
        updatedIncome.setDescription(description);

        // Call the DAO method to update the income
        IncomeDao incomeDao = new IncomeDao(ConnectionProvider.getConnection());
        incomeDao.updateIncome(updatedIncome);
        
        
        boolean isIncomeUpdated = incomeDao.updateIncome(updatedIncome);

        if (isIncomeUpdated) {
            // EMI added successfully, redirect to a success page
            Message msg = new Message("success", "Income data Updated successfully.", "alert-success");
            HttpSession session1 = request.getSession();
            session1.setAttribute("msg", msg);
            response.sendRedirect("addincome.jsp"); // Replace with your success page
        } else {
            // Handle the case where the EMI couldn't be added, possibly show an error message
            response.sendRedirect("error.jsp");
        }

    }
}
