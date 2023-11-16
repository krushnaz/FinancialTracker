package com.financialtracker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.financialtracker.entities.Category;
import com.financialtracker.util.*;

public class CategoryDao {
    // Retrieve categories from the database and store them in an ArrayList
    public List<Category> getAllIncomeCategories() {
        List<Category> categories = new ArrayList<>();
        Connection con = ConnectionProvider.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT IncomeCategoryID, IncomeCategoryName FROM IncomeCategories";
            preparedStatement = con.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int categoryID = resultSet.getInt("IncomeCategoryID");
                String categoryName = resultSet.getString("IncomeCategoryName");

                Category category = new Category(categoryID, categoryName);
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
         
        }

        return categories;
    }
    
    // expense categories
    
    public List<Category> getAllExpenseCategories() {
        List<Category> categories = new ArrayList<>();
        Connection con = ConnectionProvider.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT ExpenseCategoryID, ExpenseCategoryName FROM ExpenseCategories";
            preparedStatement = con.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int categoryID = resultSet.getInt("ExpenseCategoryID");
                String categoryName = resultSet.getString("ExpenseCategoryName");

                Category category = new Category(categoryID, categoryName);
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
         
        }

        return categories;
    }
    
    //investment category
    
    public List<Category> getAllInvestmentCategories() {
        List<Category> categories = new ArrayList<>();
        Connection con = ConnectionProvider.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT InvestmentCategoryID, InvestmentCategoryName FROM InvestmentCategories";
            preparedStatement = con.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int categoryID = resultSet.getInt("InvestmentCategoryID");
                String categoryName = resultSet.getString("InvestmentCategoryName");

                Category category = new Category(categoryID, categoryName);
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
         
        }

        return categories;
    }
    
    //loan categories
    
    public List<Category> getAllLoanCategories() {
        List<Category> categories = new ArrayList<>();
        Connection con = ConnectionProvider.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT DebtCategoryID , DebtCategoryName FROM DebtCategories";
            preparedStatement = con.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int categoryID = resultSet.getInt("DebtCategoryID");
                String categoryName = resultSet.getString("DebtCategoryName");

                Category category = new Category(categoryID, categoryName);
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
         
        }

        return categories;
    }
    
    //SIP categories
    
    public List<Category> getAllSIPCategories() {
        List<Category> categories = new ArrayList<>();
        Connection con = ConnectionProvider.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT 	SIPCategoryID , SIPCategoryName	 FROM SIPCategory";
            preparedStatement = con.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int categoryID = resultSet.getInt("SIPCategoryID");
                String categoryName = resultSet.getString("SIPCategoryName");

                Category category = new Category(categoryID, categoryName);
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
         
        }

        return categories;
    }
    
    //EMI categories
    public List<Category> getAllEMICategories() {
        List<Category> categories = new ArrayList<>();
        Connection con = ConnectionProvider.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT d.DebtCategoryID, c.DebtCategoryName FROM Debts d "
                + "INNER JOIN DebtCategories c ON d.DebtCategoryID = c.DebtCategoryID";
            preparedStatement = con.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int categoryID = resultSet.getInt("DebtCategoryID");
                String categoryName = resultSet.getString("DebtCategoryName");

                Category category = new Category(categoryID, categoryName);
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close your resources (preparedStatement, resultSet, and the connection) here
        }

        return categories;
    }
    
    
    public int getDebtIDByCategoryID(int debtCategoryID) {
        int debtID = -1; // Initialize with a default value or error code

        Connection con = ConnectionProvider.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT DebtID FROM Debts WHERE DebtCategoryID = ?";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, debtCategoryID);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                debtID = resultSet.getInt("DebtID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close your resources (preparedStatement, resultSet, and the connection) here
        }

        return debtID;
    }

}
