package com.financialtracker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Date;
import com.financialtracker.entities.User;
import com.financialtracker.util.ConnectionProvider;

public class UserDao {
	private Connection con;
    String UserName;
	public UserDao(Connection con) {
		// TODO Auto-generated constructor stub
		this.con = con;
	}

	public UserDao() {
		// TODO Auto-generated constructor stub
	}

	public boolean RegisterUser(User user) {
		boolean flag = false;
		try {
			String query = "insert into Users(Email,Password,FirstName,LastName,PhoneNumber,ProfilePictureURL,Gender) values (?,?,?,?,?,?,?)";
			PreparedStatement pstmt = this.con.prepareStatement(query);
			pstmt.setString(1, user.getEmail());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getFirstName());
			pstmt.setString(4, user.getLastName());
//	            pstmt.setDate(5, user.getDOB());
			pstmt.setLong(5, user.getPhoneNumber());
			pstmt.setString(6, user.getProfilePicture());
			pstmt.setString(7, user.getGender());

			pstmt.executeUpdate();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public User getUserByEmailAndPassword(String userEmail, String userPassword) {
		// TODO Auto-generated method stub

		User user = null;

		try {

			String query = "select * from Users where Email =? and Password=?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, userEmail);
			pstmt.setString(2, userPassword);

			ResultSet set = pstmt.executeQuery();

			if (set.next()) {
				user = new User();

//	             set to user object
				user.setEmail(set.getString("Email"));
				user.setPassword(set.getString("Password"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;

	}

	public boolean isUserEmailExist(String email) {
	    String query = "SELECT COUNT(*), FirstName FROM Users WHERE Email = ?";
	    
	    try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
	        preparedStatement.setString(1, email);

	        ResultSet resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
	            int count = resultSet.getInt(1);
	            if (count > 0) {
	                String firstName = resultSet.getString("FirstName");
	                UserName = firstName;
	               
	                
	                return true; // The email exists, and you have the first name
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return false; // Return false in case of an error or if the email doesn't exist
	}


	
	public String getUserNameByEmail(String email) {
String query = "SELECT COUNT(*), FirstName,LastName FROM Users WHERE Email = ?";
	    
	    try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
	        preparedStatement.setString(1, email);

	        ResultSet resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
	            int count = resultSet.getInt(1);
	            if (count > 0) {
	                String firstName = resultSet.getString("FirstName");
	                String LastName = resultSet.getString("LastName");
	                String fullName = firstName +" "+LastName;
	                return fullName; // The email exists, and you have the first name
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return null; 
	}
	
	public int getUserIDByEmail(String email) {
String query = "SELECT COUNT(*), UserID FROM Users WHERE Email = ?";
	    
	    try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
	        preparedStatement.setString(1, email);

	        ResultSet resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
	            int count = resultSet.getInt(1);
	            if (count > 0) {
	                int UserID = resultSet.getInt("UserId");
	                System.out.println("user id :"+UserID);
	                
	                return UserID; // The email exists, and you have the first name
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return -1; 
	}

	public boolean updatePasswordByEmail(String email, String newPassword) {
        String updateQuery = "UPDATE Users SET Password = ? WHERE Email = ?";

        try (PreparedStatement preparedStatement = con.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, email);

            int rowsUpdated = preparedStatement.executeUpdate();

            return rowsUpdated > 0; // Return true if at least one row was updated
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Handle any database-related exceptions here
        }
    }	
	
	public User getUserProfileDetails(int userID) {
	    User user = new User();

	    try {
	        String query = "SELECT * FROM Users WHERE UserID = ?";
	        PreparedStatement pstmt = con.prepareStatement(query);
	        pstmt.setInt(1, userID);

	        ResultSet resultSet = pstmt.executeQuery();

	        if (resultSet.next()) {
	            // Retrieve user details from the ResultSet and set them to the User object
	            user.setUserId(resultSet.getInt("UserID"));
	            user.setEmail(resultSet.getString("Email"));
	            user.setPassword(resultSet.getString("Password"));
	            user.setFirstName(resultSet.getString("FirstName"));
	            user.setLastName(resultSet.getString("LastName"));
	            user.setPhoneNumber(resultSet.getLong("PhoneNumber"));
	            user.setProfilePicture(resultSet.getString("ProfilePictureURL"));
	            user.setGender(resultSet.getString("Gender"));
	            System.out.println("User ID from profile: " + userID);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return user;
	}

	public boolean updateUser(User updatedUser, int userID) {
	    String query = "UPDATE Users SET FirstName=?, LastName=?, PhoneNumber=?, Gender=?, ProfilePictureURL=? WHERE UserID=?";
	    
	    try (
	         PreparedStatement preparedStatement = con.prepareStatement(query)) {
	        
	        preparedStatement.setString(1, updatedUser.getFirstName());
	        preparedStatement.setString(2, updatedUser.getLastName());
	        preparedStatement.setLong(3, updatedUser.getPhoneNumber());
	        preparedStatement.setString(4, updatedUser.getGender());
	        preparedStatement.setString(5, updatedUser.getProfilePicture());
	        preparedStatement.setInt(6, userID);

	        int rows = preparedStatement.executeUpdate();
	        return rows > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	public String getCorrectPasswordFromDatabase(int userID) {
		 User user = new User();
		 String password = "";

		    try {
		        String query = "SELECT Password FROM Users WHERE UserID = ?";
		        PreparedStatement pstmt = con.prepareStatement(query);
		        pstmt.setInt(1, userID);

		        ResultSet resultSet = pstmt.executeQuery();

		        if (resultSet.next()) {
		            // Retrieve user details from the ResultSet and set them to the User object
		        
		           password = resultSet.getString("Password");
		          
		            System.out.println("User ID from profile: " + userID);
		        }

		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		    return password;
	}

	public boolean changePassword(Integer userID, String newPassword) {
		// TODO Auto-generated method stub
		String updateQuery = "UPDATE Users SET Password = ? WHERE UserID = ?";

        try (PreparedStatement preparedStatement = con.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, newPassword);
            preparedStatement.setInt(2, userID);

            int rowsUpdated = preparedStatement.executeUpdate();

            return rowsUpdated > 0; // Return true if at least one row was updated
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Handle any database-related exceptions here
        }
	}


	 
}
