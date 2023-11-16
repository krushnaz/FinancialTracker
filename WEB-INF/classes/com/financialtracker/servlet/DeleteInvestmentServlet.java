package com.financialtracker.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.financialtracker.dao.InvestmentDao;
import com.financialtracker.entities.Message;
import com.financialtracker.dao.*;
import com.financialtracker.util.*;

@WebServlet("/DeleteInvestmentServlet")
public class DeleteInvestmentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int investmentID = Integer.parseInt(request.getParameter("investmentID"));
            System.out.println(investmentID);

            // Delete the investment record based on the investmentID
            InvestmentDao investmentDao = new InvestmentDao(ConnectionProvider.getConnection());
            boolean deleted = investmentDao.deleteInvestment(investmentID);

            if (deleted) {
                // Record deleted successfully
                // Redirect to a success page or perform any other actions
                Message msg = new Message("success", "Investment data deleted successfully", "alert-success");
                HttpSession session = request.getSession();
                session.setAttribute("msg", msg);
                response.sendRedirect("addinvestment.jsp"); // Replace with the appropriate page
            } else {
                // Handle the case where deletion failed, e.g., show an error message
                Message msg = new Message("error", "Failed to delete Investment data", "alert-danger");
                HttpSession session = request.getSession();
                session.setAttribute("msg", msg);
                response.sendRedirect("addinvestment.jsp"); // Replace with the appropriate page
            }
        } catch (NumberFormatException e) {
            // Handle invalid investmentID parameter (not an integer)
            Message msg = new Message("error", "Invalid Investment ID", "alert-danger");
            HttpSession session = request.getSession();
            session.setAttribute("msg", msg);
            response.sendRedirect("addinvestment.jsp"); // Replace with the appropriate page
        }
    }
}
