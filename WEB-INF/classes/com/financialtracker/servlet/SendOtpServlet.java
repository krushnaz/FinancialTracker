package com.financialtracker.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import java.util.Random;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.financialtracker.dao.UserDao;
import com.financialtracker.util.ConnectionProvider;
import com.financialtracker.entities.User;

@WebServlet("/sendOTP")
public class SendOtpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String username;
	private boolean isEmailInDatabase(String email) {
		// You need to obtain a valid database connection here
		UserDao dao = new UserDao(ConnectionProvider.getConnection());
		username = dao.getUserNameByEmail(email);
		System.out.println("send opt servelet :"+username);
		return dao.isUserEmailExist(email);
	}
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
	        String email = request.getParameter("reg-email");

	        if (isEmailInDatabase(email)) {
	            String otp = generateOTP();

	            boolean otpSent = sendOTPEmail(email, otp);

	            if (otpSent) {
	                HttpSession session = request.getSession();
	                session.setAttribute("otp", otp);
	                session.setAttribute("email", email);
	                response.getWriter().write("success"); // Send a success response to the client
	            } else {
	                response.getWriter().write("failure"); // Send a failure response to the client
	            }
	        } else {
	            response.sendRedirect("errorPage.jsp");
	        }
	    }

	private String generateOTP() {
		Random random = new Random();
		int otpNumber = 1000 + random.nextInt(9000);
		return String.valueOf(otpNumber);
	}

	public boolean sendOTPEmail(String recipientEmail, String otp) {
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

		
		
		
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(senderEmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
			message.setSubject("Subject: Password Reset Request for Your Financial Tracker Account");
			message.setText(

					"Dear " + username + ",\n" + "\n"
							+ "You have requested to reset the password for your Financial Tracker account. To complete this process, please enter the following OTP (One-Time Password) within the app:\n"
							+ "\n" + "OTP: "+otp+"\n" + "\n"
							+ "This OTP is valid for a single use and will expire after 1 minutes. Please do not share this OTP with anyone, as it is for your account's security.\n"
							+ "\n"
							+ "If you didn't request this password reset, or if you believe this request was made in error, please contact our support team immediately at "+senderEmail+".\n"
							+ "\n" + "Thank you for using Financial Tracker.\n" + "\n" + "Warm regards,\n"
							+ "Krushna Zarekar and Sangram Patil\n");

			Transport.send(message);
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
	}

	
}
