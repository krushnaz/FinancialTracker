package com.financialtracker.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import com.financialtracker.dao.UserDao; // Import your UserDao
import com.financialtracker.util.ConnectionProvider;
import com.financialtracker.entities.*;
@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Step 1: Retrieve input parameters
    	HttpSession session = request.getSession();
        Integer userID = (Integer) session.getAttribute("UserID");
        String newPassword = request.getParameter("password");
     
        System.out.println("password for reset "+newPassword);
      
//String newPassword = "12345";
//String email = "krishnazarekar72@gmail.com";
        // Check if newPassword and email are not null or empty
        if (newPassword != null && !newPassword.isEmpty()) {
        	
            // Step 2: Validate input and user authorization (you can add your own logic here)
            
            	
                // Step 3: Update the user's password in the database
        	UserDao dao = new UserDao(ConnectionProvider.getConnection());
                boolean passwordUpdated = dao.changePassword(userID,newPassword);

                if (passwordUpdated) {
                
                    // Step 4: Send a success response
					/*
					 * System.out.println("password reset sucessful");
					 * response.sendRedirect("login.jsp"); response.getWriter().write("success");
					 */
                	
                	// After successfully resetting the password
                	String successMessage = "Password Update successfully.";
                	Message successMsg = new Message(successMessage, "Password Update successfully.", "alert-success");
                	HttpSession session1 = request.getSession();
                	session1.setAttribute("msg", successMsg);
                	response.sendRedirect("account.jsp");

                } else {
                	response.sendRedirect("error.jsp");
                    response.getWriter().write("error"); // Handle errors, such as failed database updates
                }
            } else {
            	response.sendRedirect("error.jsp");
                response.getWriter().write("unauthorized"); // Handle validation or authorization errors
            }
        } 
    

 
}
