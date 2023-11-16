package com.financialtracker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.http.HttpSession;

import com.financialtracker.entities.*;

public class EMIDao {
	private Connection con;

	public EMIDao(Connection con) {
		this.con = con;
	}

	// Add more methods as needed for EMI-related operations

	public boolean addEMI(EMI emi) {
		boolean result = false;
		String query = "INSERT INTO EMI (DebtID, UserID, PaymentDate, Amount, PaymentMethod, Notes, EMICategoryID) VALUES (?, ?, ?, ?, ?, ?, ?)";

		try (PreparedStatement pstmt = con.prepareStatement(query)) {
			pstmt.setInt(1, emi.getDebtID());
			pstmt.setInt(2, emi.getUserID());
			pstmt.setString(3, emi.getPaymentDate());
			pstmt.setInt(4, emi.getAmount());
			pstmt.setString(5, emi.getPaymentMethod());
			pstmt.setString(6, emi.getNotes());
			pstmt.setInt(7, emi.getEMICategoryID());

			int rowsAffected = pstmt.executeUpdate();
			if (rowsAffected > 0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	// Add more methods for updating, deleting, and retrieving EMI records

	// Remember to close resources (like prepared statements and result sets) in
	// each method

	public List<EMI> getAllEMI(int UserID) {
		
		
		List<EMI> emiList = new ArrayList<>();

		try {
			String query = "SELECT e.EMIID, e.DebtID, e.UserID, e.PaymentDate, e.Amount, e.PaymentMethod, e.Notes, c.EMICategoryName " +
		               "FROM EMI e " +
		               "INNER JOIN EMICategory c ON e.EMICategoryID = c.EMICategoryID " +
		               "WHERE e.UserID = "+UserID+" "
		               +"ORDER BY e.EMIID DESC";
			PreparedStatement preparedStatement = con.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				EMI emi = new EMI();
				emi.setEMIID(resultSet.getInt("EMIID"));
				emi.setDebtID(resultSet.getInt("DebtID"));
				emi.setUserID(resultSet.getInt("UserID"));
				emi.setPaymentDate(resultSet.getString("PaymentDate"));
				emi.setAmount(resultSet.getInt("Amount"));
				emi.setPaymentMethod(resultSet.getString("PaymentMethod"));
				emi.setNotes(resultSet.getString("Notes"));
				emi.setEMIcategoryName(resultSet.getString("EMICategoryName"));

				emiList.add(emi);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// Handle exceptions as needed
		}

		return emiList;
	}

	public boolean deleteEMI(int emiID) { // Change the parameter name to match the query
		boolean deleted = false;
		try {
			String query = "DELETE FROM EMI WHERE EMIID = ?";
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, emiID); // Use emiID in the query

			int rowsDeleted = preparedStatement.executeUpdate();

			if (rowsDeleted > 0) {
				deleted = true; // Record deleted successfully
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// Handle exceptions as needed
		}
		return deleted;
	}

	// update EMI
	public boolean updateEMI(int emiId, String paymentDate, double amount, int categoryId, String paymentMode,
			String description) {
		Connection connection = null;

		String sql = "UPDATE EMI SET PaymentDate=?, Amount=?, EMICategoryID=?, PaymentMethod=?, Notes=? WHERE EMIID=?";
		try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
			// Get a database connection (Replace with your actual code to obtain a
			// connection)

			// Define the SQL update statement

			preparedStatement.setString(1, paymentDate);
			preparedStatement.setDouble(2, amount);
			preparedStatement.setInt(3, categoryId);
			preparedStatement.setString(4, paymentMode);
			preparedStatement.setString(5, description);
			preparedStatement.setInt(6, emiId);

			// Execute the update
			int rowsUpdated = preparedStatement.executeUpdate();

			// Check if any rows were updated
			if (rowsUpdated > 0) {
				// The update was successful
				return true;
			} else {
				// No rows were updated, so the update failed
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// Handle any database-related exceptions here
			return false;
		} finally {
			// Close the database resources in the finally block

		}
	}
	
	
    public List<EMI> getAllEMIAmount(int userID) {
	    List<EMI> emiAmount = new ArrayList<>();
	    int totalSIP = 0;

	    try {
	        // Replace the following SQL query with your actual query that joins the Income and IncomeCategories tables.
	    	String query = "select Amount from EMI WHERE UserID ="+userID+"";

	        PreparedStatement preparedStatement = con.prepareStatement(query);
	        ResultSet resultSet = preparedStatement.executeQuery();

	        while (resultSet.next()) {
	            EMI emi = new EMI();
	            
//                totalIncome += resultSet.getInt("Amount");
//                income.setTotalAmount(totalIncome);
	            emi.setTotalEMI(resultSet.getInt("Amount"));
	           

	            emiAmount.add(emi);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Handle exceptions as needed
	    }

	    return emiAmount;
	}

}
