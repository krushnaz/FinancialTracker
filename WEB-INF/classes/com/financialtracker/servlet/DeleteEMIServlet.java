package com.financialtracker.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.financialtracker.dao.EMIDao;
import com.financialtracker.entities.Message;
import com.financialtracker.dao.*;
import com.financialtracker.util.*;
@WebServlet("/DeleteEMIServlet")
public class DeleteEMIServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int emiID = Integer.parseInt(request.getParameter("emiID"));
System.out.println(emiID);
            // Delete the EMI record based on the emiID
            EMIDao emiDao = new EMIDao(ConnectionProvider.getConnection());
            boolean deleted = emiDao.deleteEMI(emiID);

            if (deleted) {
                // Record deleted successfully
                // Redirect to a success page or perform any other actions
                Message msg = new Message("success", "EMI data deleted successfully", "alert-success");
                HttpSession session = request.getSession();
                session.setAttribute("msg", msg);
                response.sendRedirect("EMI.jsp");
            } else {
                // Handle the case where deletion failed, e.g., show an error message
                Message msg = new Message("error", "Failed to delete EMI data", "alert-danger");
                HttpSession session = request.getSession();
                session.setAttribute("msg", msg);
                response.sendRedirect("EMI.jsp");
            }
        } catch (NumberFormatException e) {
            // Handle invalid emiID parameter (not an integer)
            Message msg = new Message("error", "Invalid EMI ID", "alert-danger");
            HttpSession session = request.getSession();
            session.setAttribute("msg", msg);
            response.sendRedirect("EMI.jsp");
        }
    }
}
