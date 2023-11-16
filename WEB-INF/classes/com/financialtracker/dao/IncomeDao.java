package com.financialtracker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.financialtracker.entities.Income;

public class IncomeDao {
	private Connection con;

	public IncomeDao(Connection con) {
		// TODO Auto-generated constructor stub
		this.con = con;
	}

	

	public boolean addIncome(Income income) {
	    boolean flag = false;
	    try {
	        String query = "INSERT INTO Income(UserID, Source, Amount, Date, IncomeCategoryID,Description ) VALUES(?, ?, ?, ?, ?, ?)";
	        PreparedStatement psmt = this.con.prepareStatement(query);
	        
	        // Set the values for the PreparedStatement
	        psmt.setInt(1, income.getUserID());
	        psmt.setString(2, income.getSource());
	        psmt.setDouble(3, income.getAmount());
	        psmt.setString(4, income.getDate());
	        psmt.setInt(5, income.getCategoryID());
//	        psmt.setBoolean(6, income.isRecurring());
	        psmt.setString(6, income.getDescription());

	        // Execute the SQL query
	        int rowsInserted = psmt.executeUpdate();

	        if (rowsInserted > 0) {
	            flag = true; // Record inserted successfully
	            System.out.println("record inserted sucessfully!");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        // Handle the exception appropriately
	    }

	    return flag;
	}
	
	// get income data
	
	public List<Income> getAllIncome(int userID) {
	    List<Income> incomeList = new ArrayList<>();

	    try {
	        // Replace the following SQL query with your actual query that joins the Income and IncomeCategories tables.
	    	String query = "SELECT i.IncomeID, i.UserID, i.source, c.IncomeCategoryName, i.Amount, i.Date, i.Description, i.Recurring " +
	                "FROM Income i " +
	                "INNER JOIN IncomeCategories c ON i.IncomeCategoryID = c.IncomeCategoryID " +" WHERE i.UserID ="+userID+" "+
	                "ORDER BY i.IncomeID DESC";

	        PreparedStatement preparedStatement = con.prepareStatement(query);
	        ResultSet resultSet = preparedStatement.executeQuery();

	        while (resultSet.next()) {
	            Income income = new Income();
	            income.setIncomeID(resultSet.getInt("IncomeID"));
	            income.setUserID(resultSet.getInt("UserID"));
	            income.setSource(resultSet.getString("Source"));
	            income.setAmount(resultSet.getDouble("Amount"));
	            income.setDate(resultSet.getString("Date"));
	            income.setCategoryName(resultSet.getString("IncomeCategoryName"));
	            income.setRecurring(resultSet.getBoolean("Recurring"));
	            income.setDescription(resultSet.getString("Description"));

	            incomeList.add(income);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Handle exceptions as needed
	    }

	    return incomeList;
	}

	
	public boolean deleteIncome(int IncomeID) { // Change the parameter name to match the query
	    boolean deleted = false;
	    try {
	        String query = "DELETE FROM Income WHERE IncomeID = ?";
	        PreparedStatement preparedStatement = con.prepareStatement(query);
	        preparedStatement.setInt(1, IncomeID); // Use emiID in the query

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



	public boolean updateIncome(Income income) {
	    String updateQuery = "UPDATE Income SET date=?, Amount=?, IncomeCategoryID=?, Source=?, Description=? WHERE IncomeID=?";

	    try (PreparedStatement preparedStatement = con.prepareStatement(updateQuery)) {
	        // Set the parameters in the prepared statement
	        preparedStatement.setString(1, income.getDate());
	        preparedStatement.setDouble(2, income.getAmount());
	        preparedStatement.setLong(3, income.getCategoryID());
	        preparedStatement.setString(4, income.getSource());
	        preparedStatement.setString(5, income.getDescription());
	        preparedStatement.setLong(6, income.getIncomeID());

	        // Execute the update
	        int rowsUpdated = preparedStatement.executeUpdate();

	        return rowsUpdated > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Handle the exception appropriately (e.g., log it or throw a custom exception)
	        return false;
	    }
	}

	
	public List<Income> getAllIncomeAmount(int userID) {
	    List<Income> incomeAmount = new ArrayList<>();
	    int totalIncome = 0;

	    try {
	        // Replace the following SQL query with your actual query that joins the Income and IncomeCategories tables.
	    	String query = "select Amount from Income WHERE UserID = "+userID+"";

	        PreparedStatement preparedStatement = con.prepareStatement(query);
	        ResultSet resultSet = preparedStatement.executeQuery();

	        while (resultSet.next()) {
	            Income income = new Income();
	            
//                totalIncome += resultSet.getInt("Amount");
//                income.setTotalAmount(totalIncome);
	           income.setTotalAmount(resultSet.getInt("Amount"));
	           

	            incomeAmount.add(income);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Handle exceptions as needed
	    }

	    return incomeAmount;
	}
	
	

}
