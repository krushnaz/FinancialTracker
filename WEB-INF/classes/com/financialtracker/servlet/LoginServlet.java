package com.financialtracker.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.financialtracker.dao.*;
import com.financialtracker.entities.*;
import com.financialtracker.util.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int UserID;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
	
		// Optionally, store other user-related data in the session

		// login
		// fetch username and password from request
		String userEmail = request.getParameter("signin-email");
		String userPassword = request.getParameter("signin-password");

		UserDao dao = new UserDao(ConnectionProvider.getConnection());
		User u = dao.getUserByEmailAndPassword(userEmail, userPassword);

		if (u == null) {
			// Invalid Details - Redirect to login page with an error message
			Message msg = new Message("error", "Invalid Details! Please try again with correct credentials.",
					"alert-danger");
			HttpSession session1 = request.getSession();
			session1.setAttribute("msg", msg);
			response.sendRedirect("login.jsp");
		} else {
			UserID = dao.getUserIDByEmail(userEmail);
			System.out.println("UserID :" + UserID);
			HttpSession session = request.getSession();
			session.setAttribute("UserID", UserID);
			System.out.println("UserID: " + UserID);

			dao.getUserProfileDetails(UserID);
			User user = new User();
			System.out.println("profile pic : "+user.getProfilePicture());
			// Login success - Set user session and redirect to the index page
			Message msg = new Message("Login successful! Welcome to Financial Tracker.", "success", "alert-success");
			HttpSession session1 = request.getSession();
			session1.setAttribute("currentUser", u);
			System.out.println("current User :" + u);
			response.sendRedirect("index.jsp");
		}
	}
}
