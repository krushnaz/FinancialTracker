package com.financialtracker.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.financialtracker.dao.CategoryDao;
import com.financialtracker.dao.EMIDao;
import com.financialtracker.entities.EMI;
import com.financialtracker.entities.Message;
import com.financialtracker.util.ConnectionProvider;

@WebServlet("/AddEMIServlet")
public class AddEMIServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddEMIServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the EMI details from the form
        String date = request.getParameter("date");
        int amount = Integer.parseInt(request.getParameter("amount"));
        int emiCategoryID = Integer.parseInt(request.getParameter("category"));
        String paymentMethod = request.getParameter("incomeMode");
        String notes = request.getParameter("notes");
        String debtCategory = request.getParameter("debtCategoryName");
       
        System.out.println(date);
        
        CategoryDao catDao = new CategoryDao();
       int DebtID = catDao.getDebtIDByCategoryID(emiCategoryID);
        
        
        // Create an EMI object with the provided details
        HttpSession session = request.getSession();
        Integer userID = (Integer) session.getAttribute("UserID");

        if (userID == null) {
            // UserID is null, set an error message
            request.setAttribute("errorMessage", "Please log in again.");

            // Forward the request to login.jsp
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return; // Stop further processing
        }

//        EMI emi = new EMI(DebtID,userID, amount, date, emiCategoryID, paymentMethod, notes);
          EMI emi = new EMI(DebtID,userID, date, amount, paymentMethod, notes,emiCategoryID);
          

        // Initialize the EMIDao with a database connection
        EMIDao emiDao = new EMIDao(ConnectionProvider.getConnection());

        // Add the EMI to the database
        boolean isEMIAdded = emiDao.addEMI(emi);

        if (isEMIAdded) {
            // EMI added successfully, redirect to a success page
            Message msg = new Message("success", "EMI data inserted successfully.", "alert-success");
            HttpSession session1 = request.getSession();
            session1.setAttribute("msg", msg);
            response.sendRedirect("EMI.jsp"); // Replace with your success page
        } else {
            // Handle the case where the EMI couldn't be added, possibly show an error message
            response.sendRedirect("error.jsp");
        }
    }
}
