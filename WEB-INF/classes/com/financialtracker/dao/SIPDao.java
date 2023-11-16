package com.financialtracker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.financialtracker.entities.Loan;
import com.financialtracker.entities.SIP;

public class SIPDao {
    private Connection con;

    public SIPDao(Connection con) {
        this.con = con;
    }

    public boolean addSIP(SIP sip) {
        boolean flag = false;
        try {
            String query = "INSERT INTO SIP(UserID, FundName, Amount, StartDate, Frequency, SIPCategoryID, PaymentMode,Description) VALUES(?, ?, ?, ?, ?, ?, ?,?)";
            PreparedStatement psmt = this.con.prepareStatement(query);

            // Set the values for the PreparedStatement
            psmt.setInt(1, sip.getUserID());
            psmt.setString(2, sip.getFundName());
            psmt.setDouble(3, sip.getAmount());
            psmt.setString(4, sip.getStartDate());
            psmt.setString(5, sip.getFrequency());
            psmt.setInt(6, sip.getSIPCategoryID());
            psmt.setString(7, sip.getPaymentMode());
            psmt.setString(8, sip.getDescription());

            // Execute the SQL query
            int rowsInserted = psmt.executeUpdate();

            if (rowsInserted > 0) {
                flag = true; // Record inserted successfully
                System.out.println("Record inserted successfully!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }

        return flag;
    }
    
    
    public List<SIP> getAllSIP(int userID) {
        List<SIP> sipList = new ArrayList<>();

        try {
        	String query = "SELECT s.SIPID, s.UserID, s.FundName, s.Amount, s.StartDate, s.Frequency, c.SIPCategoryName, s.PaymentMode,s.Description " +
                    "FROM SIP s " +
                    "INNER JOIN SIPCategory c ON s.SIPCategoryID = c.SIPCategoryID"+" WHERE s.UserID = "+userID+" ORDER BY s.SIPID DESC";
;
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                SIP sip = new SIP();
                sip.setSIPID(resultSet.getInt("SIPID"));
                sip.setUserID(resultSet.getInt("UserID"));
                sip.setFundName(resultSet.getString("FundName"));
                sip.setAmount(resultSet.getDouble("Amount"));
                sip.setStartDate(resultSet.getString("StartDate"));
                System.out.println(sip.getStartDate());
                sip.setFrequency(resultSet.getString("Frequency"));
                sip.setSIPcategoryName(resultSet.getString("SIPCategoryName"));
                sip.setPaymentMode(resultSet.getString("PaymentMode"));
                sip.setDescription(resultSet.getString("Description"));
System.out.println(sip.getUserID());
                sipList.add(sip);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions as needed
        }
           
        return sipList;
    }
    
    public boolean deleteSIP(int SIPID) { // Change the parameter name to match the query
	    boolean deleted = false;
	    try {
	        String query = "DELETE FROM SIP WHERE SIPID = ?";
	        PreparedStatement preparedStatement = con.prepareStatement(query);
	        preparedStatement.setInt(1, SIPID); // Use emiID in the query

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
    
    
    public boolean updateSIP(SIP sip) {
        String updateQuery = "UPDATE SIP SET StartDate = ?, Amount = ?, SIPCategoryID = ?, FundName = ?, Frequency = ?, PaymentMode = ?, Description = ? WHERE SIPID = ?";

        try (PreparedStatement preparedStatement = con.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, sip.getStartDate());
            preparedStatement.setDouble(2, sip.getAmount());
            preparedStatement.setInt(3, sip.getSIPCategoryID());
            preparedStatement.setString(4, sip.getFundName());
            preparedStatement.setString(5, sip.getFrequency());
            preparedStatement.setString(6, sip.getPaymentMode());
            preparedStatement.setString(7, sip.getDescription());
            preparedStatement.setInt(8, sip.getSIPID());

            int rowsUpdated = preparedStatement.executeUpdate();

            return rowsUpdated > 0; // Returns true if at least one row was updated
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    public List<SIP> getAllSIPAmount(int userID) {
	    List<SIP> sipAmount = new ArrayList<>();
	    int totalSIP = 0;

	    try {
	        // Replace the following SQL query with your actual query that joins the Income and IncomeCategories tables.
	    	String query = "select Amount from SIP WHERE UserID ="+userID+"";

	        PreparedStatement preparedStatement = con.prepareStatement(query);
	        ResultSet resultSet = preparedStatement.executeQuery();

	        while (resultSet.next()) {
	            SIP sip = new SIP();
	            
//                totalIncome += resultSet.getInt("Amount");
//                income.setTotalAmount(totalIncome);
	            sip.setTotalSIP(resultSet.getInt("Amount"));
	           

	            sipAmount.add(sip);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Handle exceptions as needed
	    }

	    return sipAmount;
	}

}
