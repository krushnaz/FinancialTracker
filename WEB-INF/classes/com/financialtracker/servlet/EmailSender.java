package com.financialtracker.servlet;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.financialtracker.entities.User;

import java.util.Properties;
import java.util.Random;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

// ... (other imports)

@WebServlet("/EmailSender")
public class EmailSender extends HttpServlet {
    private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
String email = request.getParameter("email");
		System.out.println(email);
		// Check if the email exists in the database
		
			String otp = generateOTP();

			// Send OTP to the user's email
			boolean otpSent = sendOTPEmail(email, otp);

			if (otpSent) {
				HttpSession session = request.getSession();
				session.setAttribute("otp", otp);
//				session.setAttribute("email", email);

				response.sendRedirect("login.jsp");
			} else {
				response.sendRedirect("errorPage.jsp");
			}
		} 

	private String generateOTP() {
		Random random = new Random();
		int otpNumber = 1000 + random.nextInt(9000);
		return String.valueOf(otpNumber);
	}

	public boolean sendOTPEmail(String recipientEmail , String otp) {
		
		final String senderEmail = "financialtracker.help@gmail.com"; // Replace with your email address
		final String senderPassword = "zzdg djqk holx vubp"; // Replace with your email password

		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");

		Session session = Session.getInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(senderEmail, senderPassword);
			}
		});

		User user = new User();
		
		
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(senderEmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
			message.setSubject("Subject: Email Verification Request for Your Financial Tracker Account");
			message.setText("Subject: Verify Your Email Address\n"
					+ "\n"
					+ "Dear "+recipientEmail+",\n"
					+ "\n"
					+ "Welcome to Financial Tracker! We are excited to have you on board. To complete the registration process and verify your email address, please follow the instructions below.\n"
					+ "\n"
					+ "Your temporary OTP (One-Time Password) is: "+otp+"\n"
					+ "\n"
					+ "Please use this OTP to verify your email address.\n"
					+ "\n"
					+ "Here's how to complete the verification process:\n"
					+ "\n"
					+ "1. Open the Financial Tracker app or website.\n"
					+ "2. Sign in to your account.\n"
					+ "3. In the verification section, enter the OTP you received in this email.\n"
					
					+ "\n"
					+ "Your email address will be successfully verified, and you will gain full access to your Financial Tracker.\n"
					+ "\n"
					+ "Please note:\n"
					+ "- This OTP is valid for a single use and will expire after a limited time.\n"
					+ "- Do not share this OTP with anyone for your account's security.\n"
					+ "\n"
					+ "If you did not request to verify your email address, please contact our support team immediately at "+senderEmail+" to secure your account.\n"
					+ "\n"
					+ "Thank you for choosing Financial Tracker. We are here to help you manage your finances effectively and securely.\n"
					+ "\n"
					+ "If you have any questions or need assistance, don't hesitate to reach out to our support team.\n"
					+ "\n"
					+ "Warm regards,\n"
					+ "Krushna Zarekar and Sangram Patil\n"
					
					+ "Financial Tracker Team\n"
					+ "");

			Transport.send(message);
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
	}
	}
