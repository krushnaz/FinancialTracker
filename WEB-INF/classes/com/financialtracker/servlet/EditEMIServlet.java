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

@WebServlet("/EditEMIServlet")
public class EditEMIServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditEMIServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle GET requests if needed, e.g., to display a form for editing.
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data from the request
        String emiIdStr = request.getParameter("emiId");
        String paymentDate = request.getParameter("date");
        String amountStr = request.getParameter("amount");
        String categoryIdStr = request.getParameter("category");
        String paymentMode = request.getParameter("incomeMode");
        String description = request.getParameter("notes");

        // Convert necessary data types
        int emiId = Integer.parseInt(emiIdStr);
        double amount = Double.parseDouble(amountStr);
        int categoryId = Integer.parseInt(categoryIdStr);

//         Implement code to update the EMI record in your database using a DAO class
//         For example:
         EMIDao emiDao = new EMIDao(ConnectionProvider.getConnection()); // Replace with your actual DAO class
         boolean success = emiDao.updateEMI(emiId, paymentDate, amount, categoryId, paymentMode, description);

        // Check the result of the update operation
         if (success) {
        	  System.out.println("data Updated sucessfully");
        	  Message msg = new Message("success", "EMI data Updated sucessfully.", "alert-success");
          	HttpSession session1 = request.getSession();
          	session1.setAttribute("msg", msg);
             // Redirect to a success page
             response.sendRedirect("EMI.jsp");
         } else {
             // Redirect to an error page
             response.sendRedirect("error.jsp");
         }
    }
}

