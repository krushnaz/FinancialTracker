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
@WebServlet("/resetPassword")
public class ResetPasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Step 1: Retrieve input parameters
        String newPassword = request.getParameter("new-password");
        String email = request.getParameter("reg-email");
        System.out.println("password for reset "+newPassword);
        System.out.println("email for reset passoword "+email);
//String newPassword = "12345";
//String email = "krishnazarekar72@gmail.com";
        // Check if newPassword and email are not null or empty
        if (newPassword != null && !newPassword.isEmpty() && email != null && !email.isEmpty()) {
        	
            // Step 2: Validate input and user authorization (you can add your own logic here)
            if (isValidInput(newPassword) && isAuthorizedToResetPassword(email)) {
            	
                // Step 3: Update the user's password in the database
                boolean passwordUpdated = updatePasswordInDatabase(email, newPassword);

                if (passwordUpdated) {
                
                    // Step 4: Send a success response
					/*
					 * System.out.println("password reset sucessful");
					 * response.sendRedirect("login.jsp"); response.getWriter().write("success");
					 */
                	
                	// After successfully resetting the password
                	String successMessage = "Password reset successful. You can now log in with your new password.";
                	Message successMsg = new Message(successMessage, "Password reset successful. You can now log in with your new password.", "alert-success");
                	HttpSession session = request.getSession();
                	session.setAttribute("msg", successMsg);
                	response.sendRedirect("login.jsp");

                } else {
                	response.sendRedirect("error.jsp");
                    response.getWriter().write("error"); // Handle errors, such as failed database updates
                }
            } else {
            	response.sendRedirect("error.jsp");
                response.getWriter().write("unauthorized"); // Handle validation or authorization errors
            }
        } else {
            response.getWriter().write("missing"); // Handle missing parameters
        }
    }

    private boolean isValidInput(String newPassword) {
        // Implement validation logic here (e.g., password strength requirements)
        return newPassword.length() >= 4; // Modify this based on your requirements
    }

    private boolean isAuthorizedToResetPassword(String email) {
        // Implement authorization logic here (e.g., check if the user is allowed to reset the password)
        return true; // Modify this based on your requirements
    }

    private boolean updatePasswordInDatabase(String email, String newPassword) {
        try {
            UserDao userDao = new UserDao(ConnectionProvider.getConnection());
            return userDao.updatePasswordByEmail(email, newPassword);
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Handle any exceptions or database errors here
        }
    }
}
