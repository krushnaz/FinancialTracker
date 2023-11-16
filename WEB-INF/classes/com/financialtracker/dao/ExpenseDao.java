package com.financialtracker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.financialtracker.entities.Expense;
import com.financialtracker.entities.Income;

public class ExpenseDao {
    private Connection con;

    public ExpenseDao(Connection con) {
        this.con = con;
    }

    public boolean addExpense(Expense expense) {
        boolean flag = false;
        try {
            String query = "INSERT INTO Expenses (UserID, Description, Amount, Date,PaymentMode, ExpenseCategoryID) VALUES (?, ?, ?, ?, ?,?)";
            PreparedStatement psmt = this.con.prepareStatement(query);

            // Set the values for the PreparedStatement
            psmt.setInt(1, expense.getUserID());
            psmt.setString(2, expense.getDescription());
            psmt.setDouble(3, expense.getAmount());
            psmt.setString(4, expense.getDate());
			/* psmt.setBoolean(5, expense.isRecurring()); */
            psmt.setString(5, expense.getPaymentMode());
            psmt.setInt(6, expense.getExpenseCategoryID());

            // Execute the SQL query
            int rowsInserted = psmt.executeUpdate();

            if (rowsInserted > 0) {
                flag = true; // Record inserted successfully
                System.out.println("expense Record inserted successfully!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }

        return flag;
    }
    
    public List<Expense> getAllExpenses(int userID) {
        List<Expense> expenseList = new ArrayList<>();

        try {
            // Replace the following SQL query with your actual query that joins the Expense and ExpenseCategories tables.
            String query = "SELECT e.ExpenseID, e.UserID, e.Description, e.Amount, e.Date,e.PaymentMode, e.ExpenseCategoryID, c.ExpenseCategoryName " +
                           "FROM Expenses e " +
                           "INNER JOIN ExpenseCategories c ON e.ExpenseCategoryID = c.ExpenseCategoryID"+" WHERE e.UserID ="+userID+" "+" ORDER BY e.ExpenseID DESC";

            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Expense expense = new Expense();
                expense.setExpenseID(resultSet.getInt("ExpenseID"));
                expense.setUserID(resultSet.getInt("UserID"));
                expense.setDescription(resultSet.getString("Description"));
                expense.setAmount(resultSet.getDouble("Amount"));
                expense.setDate(resultSet.getString("Date"));
                expense.setPaymentMode(resultSet.getString("PaymentMode"));
                expense.setExpenseCategoryID(resultSet.getInt("ExpenseCategoryID"));
                expense.setExpenseCategoryName(resultSet.getString("ExpenseCategoryName"));

                expenseList.add(expense);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions as needed
        }

        return expenseList;
    }

    
    public boolean deleteExpense(int ExpenseID) { // Change the parameter name to match the query
	    boolean deleted = false;
	    try {
	        String query = "DELETE FROM Expenses WHERE ExpenseID = ?";
	        PreparedStatement preparedStatement = con.prepareStatement(query);
	        preparedStatement.setInt(1, ExpenseID); // Use emiID in the query

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
    
    
    public boolean updateExpense(Expense expense) {
	    String updateQuery = "UPDATE Expenses SET Date=?, Amount=?, ExpenseCategoryID=?, PaymentMode=?, Description=? WHERE ExpenseID=?";

	    try (PreparedStatement preparedStatement = con.prepareStatement(updateQuery)) {
	        // Set the parameters in the prepared statement
	        preparedStatement.setString(1, expense.getDate());
	        preparedStatement.setDouble(2, expense.getAmount());
	        preparedStatement.setLong(3, expense.getExpenseCategoryID());
	        preparedStatement.setString(4, expense.getPaymentMode());
	        preparedStatement.setString(5, expense.getDescription());
	        preparedStatement.setLong(6, expense.getExpenseID());

	        // Execute the update
	        int rowsUpdated = preparedStatement.executeUpdate();

	        return rowsUpdated > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Handle the exception appropriately (e.g., log it or throw a custom exception)
	        return false;
	    }
	}
    
    
    public List<Expense> getAllExpenseAmount(int userID) {
	    List<Expense> expenseAmount = new ArrayList<>();
	    int totalExpense = 0;

	    try {
	        // Replace the following SQL query with your actual query that joins the Income and IncomeCategories tables.
	    	String query = "select Amount from Expenses WHERE UserID = "+userID+"";

	        PreparedStatement preparedStatement = con.prepareStatement(query);
	        ResultSet resultSet = preparedStatement.executeQuery();

	        while (resultSet.next()) {
	            Expense expense = new Expense();
	            
//                totalIncome += resultSet.getInt("Amount");
//                income.setTotalAmount(totalIncome);
	            expense.setTotalAmount(resultSet.getInt("Amount"));
	           

	            expenseAmount.add(expense);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Handle exceptions as needed
	    }

	    return expenseAmount;
	}
	

}
