package com.financialtracker.servlet;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import com.financialtracker.entities.*;
import com.financialtracker.util.ConnectionProvider;
import com.financialtracker.dao.*;

/**
 * Servlet implementation class EditAccountServlet
 */
@WebServlet("/EditAccountServlet")
@MultipartConfig
public class EditAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String userId = request.getParameter("userId");
        String firstName = request.getParameter("f-name");
        String lastName = request.getParameter("l-name");
        long phone =Long.parseLong(request.getParameter("phone"));
        String gender = request.getParameter("gender");

        Part part = request.getPart("img");
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


        // Create a User object with the updated values
        User updatedUser = new User();
        
        updatedUser.setFirstName(firstName);
        updatedUser.setLastName(lastName);
        updatedUser.setPhoneNumber(phone);
        updatedUser.setGender(gender);
        updatedUser.setProfilePicture(fileName + fileExtension);
        HttpSession session1 = request.getSession();
        Integer userID = (Integer) session1.getAttribute("UserID");
System.out.println("user id from edit account :"+userID);
        UserDao userDao = new UserDao(ConnectionProvider.getConnection());
        boolean isAccountUpdated = userDao.updateUser(updatedUser,userID);

        if (isAccountUpdated) {
            Message msg = new Message("success", "Profile Updated successfully.", "alert-success");
            HttpSession session = request.getSession();
            session.setAttribute("msg", msg);
            response.sendRedirect("account.jsp");
        } else {
            response.sendRedirect("errorPage.jsp");
        }
    }

   
}
