package com.financialtracker.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class VerifyEmailSenderOtp
 */
@WebServlet("/verifyOTP")
public class VerifyOtpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerifyOtpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	// VerifyOTPServlet.java
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String enteredOTP = request.getParameter("enteredOTP");
	    String sessionOTP = (String) request.getSession().getAttribute("otp"); // Retrieve the stored OTP from the session
         System.out.println("entered OTP: "+enteredOTP);
         System.out.println("session OTP: "+sessionOTP);
	    if (enteredOTP.equals(sessionOTP)) {
	        response.getWriter().write("success"); // Send a success response to the client
	    } else {
	        response.getWriter().write("failure"); // Send a failure response to the client
	    }
	}

}
