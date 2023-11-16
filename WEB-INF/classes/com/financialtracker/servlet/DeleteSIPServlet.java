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
import com.financialtracker.util.*;

@WebServlet("/DeleteSIPServlet")
public class DeleteSIPServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int sipID = Integer.parseInt(request.getParameter("sipID"));
            System.out.println(sipID);

            // Delete the SIP record based on the sipID
            SIPDao sipDao = new SIPDao(ConnectionProvider.getConnection());
            boolean deleted = sipDao.deleteSIP(sipID);

            if (deleted) {
                // Record deleted successfully
                // Redirect to a success page or perform any other actions
                Message msg = new Message("success", "SIP data deleted successfully", "alert-success");
                HttpSession session = request.getSession();
                session.setAttribute("msg", msg);
                response.sendRedirect("SIP.jsp"); // Replace with the appropriate page
            } else {
                // Handle the case where deletion failed, e.g., show an error message
            	  Message msg = new Message("error", "Failed to delete Investment data", "alert-danger");
                  HttpSession session = request.getSession();
                  session.setAttribute("msg", msg);
                response.sendRedirect("SIP.jsp"); // Replace with the appropriate page
            }
        } catch (NumberFormatException e) {
            // Handle invalid sipID parameter (not an integer)
            Message msg = new Message("error", "Invalid SIP ID", "alert-danger");
            HttpSession session = request.getSession();
            session.setAttribute("msg", msg);
            response.sendRedirect("SIP.jsp"); // Replace with the appropriate page
        }
    }
}
