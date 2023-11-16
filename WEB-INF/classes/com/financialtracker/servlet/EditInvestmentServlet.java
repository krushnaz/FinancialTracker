package com.financialtracker.servlet;

import com.financialtracker.dao.InvestmentDao;
import com.financialtracker.entities.*;
import com.financialtracker.util.ConnectionProvider;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/EditInvestmentServlet")
public class EditInvestmentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditInvestmentServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // This servlet should not handle GET requests
        response.sendRedirect("index.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Get form data
            int investmentID = Integer.parseInt(request.getParameter("investmentID"));
            String date = request.getParameter("date");
            BigDecimal amount = new BigDecimal(request.getParameter("amount"));
            int categoryID = Integer.parseInt(request.getParameter("category"));
            String incomeMode = request.getParameter("incomeMode");
            String description = request.getParameter("description");
            String purchaseDate = request.getParameter("purchaseDate");
        

            // Create an Investment object with the updated data
            Investment updatedInvestment = new Investment(investmentID, date, amount, categoryID, incomeMode, description, purchaseDate);

            // Update the investment in the database
            InvestmentDao investmentDao = new InvestmentDao(ConnectionProvider.getConnection());
            boolean success = investmentDao.updateInvestment(updatedInvestment);

            if (success) {
            	Message msg = new Message("success", "Investment data Updated successfully.", "alert-success");
                HttpSession session1 = request.getSession();
                session1.setAttribute("msg", msg);
                // Redirect to a success page or display a success message
                response.sendRedirect("addinvestment.jsp");
            } else {
                // Handle the case where the update fails, e.g., show an error message
                response.sendRedirect("error.jsp");
            }
        } catch (Exception e) {
            // Handle exceptions appropriately
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
