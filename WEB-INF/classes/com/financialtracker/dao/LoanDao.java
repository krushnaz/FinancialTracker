package com.financialtracker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.financialtracker.entities.Investment;
import com.financialtracker.entities.Loan;

public class LoanDao {
    private Connection con;

    public LoanDao(Connection con) {
        this.con = con;
    }

    public boolean addLoan(Loan loan) {
        boolean result = false;
        String query = "INSERT INTO Debts (UserID, Creditor, Amount, InterestRate, PaymentSchedule,	Description, DebtCategoryID,PaymentMode,Date) " +
                "VALUES (?, ?, ?, ?, ?, ?,?,?,?)";

        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, loan.getUserID());
            pstmt.setString(2, loan.getCreditor());
            pstmt.setDouble(3, loan.getAmount());
            pstmt.setDouble(4, loan.getInterestRate());
            pstmt.setString(5, loan.getPaymentSchedule());
            pstmt.setString(6, loan.getDescription());
            pstmt.setInt(7, loan.getDebtCategoryID());
            pstmt.setString(8, loan.getPaymentMode());
            pstmt.setString(9, loan.getDate());
//            pstmt.setString(7, loan.getDebtName()); // Set the DebtName field

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
    
    
    public List<Loan> getAllLoans(int userID) {
        List<Loan> loanList = new ArrayList<>();

        try {
            String query = "SELECT d.DebtID, d.UserID, d.Creditor, d.Amount, d.InterestRate, d.PaymentSchedule,d.Description, d.DebtCategoryID,d.PaymentMode,d.Date, c.DebtCategoryName " +
                           "FROM Debts d " +
                           "INNER JOIN DebtCategories c ON d.DebtCategoryID = c.DebtCategoryID"+" WHERE d.UserID ="+userID+" ORDER BY d.DebtID DESC";

            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Loan loan = new Loan();
                loan.setDebtID(resultSet.getInt("DebtID"));
                loan.setUserID(resultSet.getInt("UserID"));
                loan.setCreditor(resultSet.getString("Creditor"));
                loan.setAmount(resultSet.getDouble("Amount"));
                loan.setInterestRate(resultSet.getDouble("InterestRate"));
                loan.setPaymentSchedule(resultSet.getString("PaymentSchedule"));
                loan.setDescription(resultSet.getString("Description"));
                loan.setDebtCategoryID(resultSet.getInt("DebtCategoryID"));
                loan.setLoanCategoryName(resultSet.getString("DebtCategoryName"));
                loan.setPaymentMode(resultSet.getString("PaymentMode"));
                loan.setDate(resultSet.getString("Date"));

                loanList.add(loan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions as needed
        }

        return loanList;
    }
    
    public boolean deleteLoan(int DebtID) { // Change the parameter name to match the query
	    boolean deleted = false;
	    try {
	        String query = "DELETE FROM Debts WHERE DebtID = ?";
	        PreparedStatement preparedStatement = con.prepareStatement(query);
	        preparedStatement.setInt(1, DebtID); // Use emiID in the query

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

    public boolean updateLoan(Loan updatedLoan) {
        String updateQuery = "UPDATE Debts SET Date=?, Amount=?, InterestRate=?, DebtCategoryID=?, PaymentSchedule=?, Creditor=?, PaymentMode=?, Description=? WHERE debtID=?";

        try (PreparedStatement preparedStatement = con.prepareStatement(updateQuery)) {
System.out.println("get date () :"+updatedLoan.getDate());
            // Set the parameters in the prepared statement
            preparedStatement.setString(1, updatedLoan.getDate());
            preparedStatement.setDouble(2, updatedLoan.getAmount());
            preparedStatement.setDouble(3, updatedLoan.getInterestRate());
            preparedStatement.setInt(4, updatedLoan.getDebtCategoryID());
            preparedStatement.setString(5, updatedLoan.getPaymentSchedule());
            preparedStatement.setString(6, updatedLoan.getCreditor());
            preparedStatement.setString(7, updatedLoan.getPaymentMode());
            preparedStatement.setString(8, updatedLoan.getDescription());
            preparedStatement.setInt(9, updatedLoan.getDebtID());

            // Execute the update
            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                // Loan updated successfully
            	System.out.println("loanDao data updated sucessfully");
                return true;
            } else {
                // Loan update failed
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately (e.g., log it or throw a custom exception)
            return false;
        }
    }
    
    public List<Loan> getAllLoanAmount(int userID) {
	    List<Loan> loanAmount = new ArrayList<>();
	    int totalloan = 0;

	    try {
	        // Replace the following SQL query with your actual query that joins the Income and IncomeCategories tables.
	    	String query = "select Amount from Debts WHERE UserID ="+userID+"";

	        PreparedStatement preparedStatement = con.prepareStatement(query);
	        ResultSet resultSet = preparedStatement.executeQuery();

	        while (resultSet.next()) {
	            Loan loan = new Loan();
	            
//                totalIncome += resultSet.getInt("Amount");
//                income.setTotalAmount(totalIncome);
	            loan.setTotalLoan(resultSet.getInt("Amount"));
	           

	            loanAmount.add(loan);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Handle exceptions as needed
	    }

	    return loanAmount;
	}


}
