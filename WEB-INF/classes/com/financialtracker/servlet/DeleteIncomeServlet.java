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
@WebServlet("/DeleteIncomeServlet")
public class DeleteIncomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int IncomeID = Integer.parseInt(request.getParameter("IncomeID"));
System.out.println(IncomeID);
            // Delete the EMI record based on the emiID
           IncomeDao income = new IncomeDao(ConnectionProvider.getConnection());
            boolean deleted = income.deleteIncome(IncomeID);

            if (deleted) {
                // Record deleted successfully
                // Redirect to a success page or perform any other actions
                Message msg = new Message("success", "Income data deleted successfully", "alert-success");
                HttpSession session = request.getSession();
                session.setAttribute("msg", msg);
                response.sendRedirect("addincome.jsp");
            } else {
                // Handle the case where deletion failed, e.g., show an error message
                Message msg = new Message("error", "Failed to delete Income data", "alert-danger");
                HttpSession session = request.getSession();
                session.setAttribute("msg", msg);
                response.sendRedirect("addincome.jsp");
            }
        } catch (NumberFormatException e) {
            // Handle invalid emiID parameter (not an integer)
            Message msg = new Message("error", "Invalid Income ID", "alert-danger");
            HttpSession session = request.getSession();
            session.setAttribute("msg", msg);
            response.sendRedirect("addincome.jsp");
        }
    }
}
