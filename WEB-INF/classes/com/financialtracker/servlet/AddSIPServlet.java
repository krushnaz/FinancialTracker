package com.financialtracker.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.financialtracker.dao.SIPDao;
import com.financialtracker.entities.SIP;
import com.financialtracker.entities.Message;
import com.financialtracker.util.ConnectionProvider;

@WebServlet("/AddSIPServlet")
public class AddSIPServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AddSIPServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the SIP details from the form
       
        double amount = Double.parseDouble(request.getParameter("amount"));
        int SIPCategoryID = Integer.parseInt(request.getParameter("category"));
        String fundName = request.getParameter("fundname");
        String startDate = request.getParameter("date");
        String frequency = request.getParameter("frequency");
        String paymentSchedule = request.getParameter("incomeMode");
        String description = request.getParameter("description");
        // Create a SIP object with the provided details
        HttpSession session = request.getSession();
        Integer UserID = (Integer) session.getAttribute("UserID");
        System.out.println("User ID is from SIP: " + UserID);
        if (UserID == null) {
            // UserID is null, set an error message
            request.setAttribute("errorMessage", "Please log in again.");

            // Forward the request to login.jsp
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return; // Stop further processing
        }

        SIP sip = new SIP( UserID, fundName, amount, startDate, frequency,SIPCategoryID,paymentSchedule,description);

        // Initialize the SIPDao with a database connection
        SIPDao sipDao = new SIPDao(ConnectionProvider.getConnection());

        // Add the SIP to the database
        boolean isSIPAdded = sipDao.addSIP(sip);

        if (isSIPAdded) {
            // SIP added successfully, redirect to a success page
            System.out.println("SIP data inserted successfully");
            Message msg = new Message("success", "SIP data inserted successfully", "alert-success");
            HttpSession session1 = request.getSession();
            session1.setAttribute("msg", msg);
            response.sendRedirect("SIP.jsp");
        } else {
            // Handle the case where the SIP couldn't be added, possibly show an error message
            response.sendRedirect("error.jsp");
        }
    }
}
