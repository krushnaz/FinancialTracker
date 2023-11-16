package com.financialtracker.servlet;

import com.financialtracker.entities.*;
import com.financialtracker.dao.*;
import com.financialtracker.util.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
//        String date = request.getParameter("dob");
        long phoneNumber = Long.parseLong(request.getParameter("phoneNumber"));
        Part part = request.getPart("profilePicture");
        String gender = request.getParameter("gender");

//        String dobString = date; // Replace with your user input
//
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // Save the profile picture to the "profile_pics" folder
		String uploadedFileName = part.getSubmittedFileName();
		String fileName = "_" + System.currentTimeMillis(); // Unique name
		String fileExtension = "";  // Default value for an empty or invalid file name

		if (uploadedFileName != null && !uploadedFileName.isEmpty()) {
		    fileExtension = uploadedFileName.substring(uploadedFileName.lastIndexOf("."));
		} else {
		    // Handle the case where the file name is empty or invalid.
		}

		String filePath = getServletContext().getRealPath("/") + "assets/images/profiles/" + fileName + fileExtension;
		System.out.println(filePath);

		part.write(filePath);

		// Update the database with the file path
		User user = new User(firstName, lastName, email, phoneNumber, fileName + fileExtension, gender, password);
		UserDao dao = new UserDao(ConnectionProvider.getConnection());

		if (dao.RegisterUser(user)) {
			/* out.print("done"); */
			response.sendRedirect("login.jsp");
		} else {
		    out.print("<!DOCTYPE html>\n"
		    		+ "<html lang=\"en\">\n"
		    		+ "\n"
		    		+ "<head>\n"
		    		+ "	<meta charset=\"utf-8\">\n"
		    		+ "	<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
		    		+ "	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
		    		+ "	<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->\n"
		    		+ "\n"
		    		+ "	<title>Error</title>\n"
		    		+ "\n"
		    		+ "	<!-- Google font -->\n"
		    		+ "	<link href=\"https://fonts.googleapis.com/css?family=Nunito:400,700\" rel=\"stylesheet\">\n"
		    		+ "\n"
		    		+ "	<!-- Custom stlylesheet -->\n"
		    		+ "	<link type=\"text/css\" rel=\"stylesheet\" href=\"assets/css/style.css\" />\n"
		    		+ "\n"
		    		+ "	<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->\n"
		    		+ "	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->\n"
		    		+ "	<!--[if lt IE 9]>\n"
		    		+ "		<![endif]-->\n"
		    		+ "		  <script src=\"https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js\"></script>\n"
		    		+ "		  <script src=\"https://oss.maxcdn.com/respond/1.4.2/respond.min.js\"></script>\n"
		    		+ "\n"
		    		+ "</head>\n"
		    		+ "\n"
		    		+ "<body>\n"
		    		+ "\n"
		    		+ "	<div id=\"notfound\">\n"
		    		+ "		<div class=\"notfound\">\n"
		    		+ "			<div class=\"notfound-404\"></div>\n"
		    		+ "			<h1></h1>\n"
		    		+ "			<h2>Email Already Registered</h2>\n"
		    		+ "	<p>\"We're sorry, but the email address you provided is already registered with our system. Please use a different email address or try to log in using the existing account associated with this email.\"</p> \n"
		    		+ "			<p> </p>\n"
		    		+ "			<a href=\"login.jsp\">Back to homepage</a>\n"
		    		+ "		</div>\n"
		    		+ "	</div>\n"
		    		+ "\n"
		    		+ "</body><!-- This templates was made by Colorlib (https://colorlib.com) -->\n"
		    		+ "\n"
		    		+ "</html>");
		}
    }
}
