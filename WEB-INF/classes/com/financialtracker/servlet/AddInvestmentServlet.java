package com.financialtracker.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.financialtracker.dao.InvestmentDao;
import com.financialtracker.entities.*;
import com.financialtracker.util.ConnectionProvider;
@WebServlet("/AddInvestmentServlet")
public class AddInvestmentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AddInvestmentServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the data from the request parameters
    	String date = request.getParameter("date");
    	
        double amount = Double.parseDouble(request.getParameter("amount"));
        int categoryID = Integer.parseInt(request.getParameter("category"));
        String incomeMode = request.getParameter("incomeMode");
        String purchaseDate = request.getParameter("PurchaseDate");
        String description = request.getParameter("w3review");
        // TODO: Get the UserID. You can obtain this information based on your application's authentication mechanism.
        HttpSession session = request.getSession();
        Integer UserID = (Integer) session.getAttribute("UserID");
        System.out.println("user id is :"+UserID);
        if (UserID == null) {
            // UserID is null, set an error message
            request.setAttribute("errorMessage", "Please log in again.");

            // Forward the request to login.jsp
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return; // Stop further processing
        }
        Investment invest = new Investment(UserID,amount,date,categoryID,incomeMode,purchaseDate,description);
        InvestmentDao investment = new InvestmentDao(ConnectionProvider.getConnection());
          if(investment.addInvestment(invest)) {
        	  System.out.println("data inserted sucessfully");
        	  Message msg = new Message("success", "Investment data inserted sucessfully.", "alert-success");
          	HttpSession session1 = request.getSession();
          	session1.setAttribute("msg", msg);
        	  response.sendRedirect("addinvestment.jsp");
          }else {
        	  System.out.println("error occur while inserting data");
        	  response.sendRedirect("error.jsp");
          }

        // Create an Income object and set its properties
       
    }
}
