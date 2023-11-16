package com.financialtracker.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.financialtracker.dao.UserDao;
import com.financialtracker.entities.*;
import com.financialtracker.util.ConnectionProvider;

@WebServlet("/VerifyPasswordServlet")
public class VerifyPasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain"); // Set the response content type to plain text
        PrintWriter out = response.getWriter();
      HttpSession session = request.getSession();
      Integer userID = (Integer) session.getAttribute("UserID");
		String enteredPassword = request.getParameter("password"); // Get the entered password from the request
//      String enteredPassword = "12345";
        System.out.println("entered password :"+enteredPassword);
        // Fetch the correct password from the database using your DAO
        UserDao dao = new UserDao(ConnectionProvider.getConnection());
        User user = new User(); 
        String correctPassword = dao.getCorrectPasswordFromDatabase(userID);
        System.out.println("Correct password: " + correctPassword);

        if (correctPassword != null && correctPassword.equals(enteredPassword)) {
            out.print("success");
            // You can redirect or send a success response here
        } else {
            out.print("failure");
            // You can redirect or send an error response here
        }
    }
}
