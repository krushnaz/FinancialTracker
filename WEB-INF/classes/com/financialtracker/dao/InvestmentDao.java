package com.financialtracker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.financialtracker.entities.Expense;
import com.financialtracker.entities.Investment;

public class InvestmentDao {
    private Connection con;

    public InvestmentDao(Connection con) {
        this.con = con;
    }

    public boolean addInvestment(Investment investment) {
        boolean result = false;
        String query = "INSERT INTO Investments (UserID, InvestmentCategoryID, Date, PurchaseDate, Amount, Description, PaymentMode) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
   
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, investment.getUserID());
            pstmt.setInt(2, investment.getInvestmentCategoryID());
            pstmt.setString(3, investment.getDate());
            pstmt.setString(4, investment.getPurchaseDate());
            pstmt.setBigDecimal(5, investment.getAmount());
            pstmt.setString(6, investment.getDescription());
            pstmt.setString(7, investment.getPaymentMode());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any exceptions here
        }
        return result;
    }
    
    public List<Investment> getAllInvestments(int userID) {
        List<Investment> investments = new ArrayList<>();

        try {
        	String query = "SELECT i.InvestmentID, i.UserID, i.InvestmentCategoryID, c.InvestmentCategoryName, i.PurchaseDate, i.Amount, i.Description, i.PaymentMode, i.Date\n"
        			+ "FROM Investments i\n"
        			+ "INNER JOIN InvestmentCategories c ON i.InvestmentCategoryID = c.InvestmentCategoryID"+" WHERE i.UserID ="+userID+" "+" ORDER BY i.InvestmentID DESC";

            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
            	Investment investment = new Investment();
            	investment.setInvestmentID(resultSet.getInt("InvestmentID"));
            	investment.setUserID(resultSet.getInt("UserID"));
            	investment.setDate(resultSet.getString("Date"));
            	investment.setInvestmentCategoryID(resultSet.getInt("InvestmentCategoryID"));
            	investment.setPurchaseDate(resultSet.getString("PurchaseDate"));
            	investment.setAmount(resultSet.getBigDecimal("Amount"));
            	investment.setDescription(resultSet.getString("Description"));
            	investment.setPaymentMode(resultSet.getString("PaymentMode"));

                investment.setInvestmentCategoryName(resultSet.getString("InvestmentCategoryName"));

                investments.add(investment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions as needed
        }

        return investments;
    }
    
    public boolean deleteInvestment(int InvestmentID) { // Change the parameter name to match the query
	    boolean deleted = false;
	    try {
	        String query = "DELETE FROM Investments WHERE InvestmentID = ?";
	        PreparedStatement preparedStatement = con.prepareStatement(query);
	        preparedStatement.setInt(1, InvestmentID); // Use emiID in the query

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

    public boolean updateInvestment(Investment investment) {
        String updateQuery = "UPDATE Investments SET Date=?, Amount=?, InvestmentCategoryID=?, PaymentMode=?, Description=?, PurchaseDate=? WHERE InvestmentID=?";

        try (PreparedStatement preparedStatement = con.prepareStatement(updateQuery)) {
	        preparedStatement.setString(1, investment.getDate());
            preparedStatement.setBigDecimal(2, investment.getAmount());
            preparedStatement.setLong(3, investment.getInvestmentCategoryID());
            preparedStatement.setString(4, investment.getPaymentMode());
            preparedStatement.setString(5, investment.getDescription());
	        preparedStatement.setString(6, investment.getPurchaseDate());

            preparedStatement.setLong(7, investment.getInvestmentID());

            int rowsUpdated = preparedStatement.executeUpdate();

            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately, e.g., log it or throw a custom exception
            return false;
        }
    }

    public List<Investment> getAllInvestmentAmount(int userID) {
	    List<Investment> investmentAmount = new ArrayList<>();
	    int totalInvestment = 0;

	    try {
	        // Replace the following SQL query with your actual query that joins the Income and IncomeCategories tables.
	    	String query = "select Amount from Investments WHERE UserID ="+userID+"";

	        PreparedStatement preparedStatement = con.prepareStatement(query);
	        ResultSet resultSet = preparedStatement.executeQuery();

	        while (resultSet.next()) {
	            Investment investment = new Investment();
	            
//                totalIncome += resultSet.getInt("Amount");
//                income.setTotalAmount(totalIncome);
	            investment.setTotalAmount(resultSet.getInt("Amount"));
	           

	            investmentAmount.add(investment);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Handle exceptions as needed
	    }

	    return investmentAmount;
	}

}
