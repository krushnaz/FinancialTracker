package com.financialtracker.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.financialtracker.dao.IncomeDao;
import com.financialtracker.entities.*;
import com.financialtracker.util.ConnectionProvider;
@WebServlet("/AddIncomeServlet")
public class AddIncomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AddIncomeServlet() {
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
        String source = request.getParameter("incomeMode");
//        boolean recurring = Boolean.parseBoolean(request.getParameter("recurring"));
        String description = request.getParameter("description");
        System.out.println(source); 
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
        Income income = new Income(UserID,source,amount,date,categoryID,description);
        IncomeDao incomedao = new IncomeDao(ConnectionProvider.getConnection());
          if(incomedao.addIncome(income)) {
        	  System.out.println("data inserted sucessfully");
        	  Message msg = new Message("success", "Income data inserted sucessfully.", "alert-success");
          	HttpSession session1 = request.getSession();
          	session1.setAttribute("msg", msg);
        	  response.sendRedirect("addincome.jsp");
          }else {
        	  System.out.println("error occur while inserting data");
        	  response.sendRedirect("error.jsp");
          }

        // Create an Income object and set its properties
       
    }
}
