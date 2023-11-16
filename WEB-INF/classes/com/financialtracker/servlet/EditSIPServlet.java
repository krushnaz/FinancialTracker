package com.financialtracker.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.financialtracker.dao.SIPDao;
import com.financialtracker.entities.Message;
import com.financialtracker.entities.SIP;
import com.financialtracker.util.ConnectionProvider;

@WebServlet("/EditSIPServlet")
public class EditSIPServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve parameters from the form
        int sipID = Integer.parseInt(request.getParameter("sipID"));
        String startDate = request.getParameter("date");
        double amount = Double.parseDouble(request.getParameter("amount"));
        int categoryID = Integer.parseInt(request.getParameter("category"));
        String fundName = request.getParameter("fundname");
        String frequency = request.getParameter("frequency");
        String paymentMode = request.getParameter("incomeMode");
        String description = request.getParameter("description");

        // Create a SIP object with the updated information
     

        // Update the SIP record in the database
        SIPDao sipDao = new SIPDao(ConnectionProvider.getConnection()); // Adjust this to your connection handling
        SIP updatedSIP = new SIP(sipID,startDate,amount,categoryID,fundName,frequency,paymentMode,description);
        boolean success = sipDao.updateSIP(updatedSIP);

        if (success) {
      	  System.out.println("data Updated sucessfully");
    	  Message msg = new Message("success", "SIP data Updated sucessfully.", "alert-success");
      	HttpSession session1 = request.getSession();
      	session1.setAttribute("msg", msg);
            // Redirect to a success page or display a success message
            response.sendRedirect("SIP.jsp");
        } else {
            // Redirect to an error page or display an error message
            response.sendRedirect("error.jsp");
        }
    }
}
