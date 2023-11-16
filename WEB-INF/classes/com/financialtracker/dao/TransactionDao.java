package com.financialtracker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.*;

import com.financialtracker.entities.Transaction;

public class TransactionDao {
	private Connection connection;
	private int UserID;

	public TransactionDao(Connection connection) {
		
		this.connection = connection;
	}

	public List<Transaction> getAllIncome(int userID) {
		UserID = userID;
		return fetchDataFromTable("Income");
	}

	public List<Transaction> getAllExpenses(int userID) {
		UserID = userID;
		return fetchDataFromTable("Expenses");
	}

	public List<Transaction> getAllInvestments(int userID) {
		UserID = userID;
		return fetchDataFromTable("Investments");
	}

	public List<Transaction> getAllDebts(int userID) {
		UserID = userID;
		return fetchDataFromTable("Debts");
	}

	public List<Transaction> getAllSIP(int userID) {
		UserID = userID;
		return fetchDataFromTable("SIP");
		
	}

	public List<Transaction> getAllEMI(int userID) {
		UserID = userID;
		return fetchDataFromTable("EMI");
	}

	private List<Transaction> fetchDataFromTable(String tableName) {
		List<Transaction> transactions = new ArrayList<>();
//		String query = "SELECT * FROM " + tableName;

		Map<String, String> categoryTableMap = new HashMap<>();
		categoryTableMap.put("Income", "IncomeCategories");
		categoryTableMap.put("Expenses", "ExpenseCategories");
		categoryTableMap.put("Investments", "InvestmentCategories");
		categoryTableMap.put("Debts", "DebtCategories");
		categoryTableMap.put("SIP", "SIPCategory");
		categoryTableMap.put("EMI", "EMICategory");
		// Add mappings for other transaction tables as needed

		Map<String, String> categoryNameFieldMap = new HashMap<>();
		categoryNameFieldMap.put("Income", "IncomeCategoryName");
		categoryNameFieldMap.put("Expenses", "ExpenseCategoryName");
		categoryNameFieldMap.put("Investments", "InvestmentCategoryName");
		categoryNameFieldMap.put("Debts", "DebtCategoryName");
		categoryNameFieldMap.put("SIP", "SIPCategoryName");
		categoryNameFieldMap.put("EMI", "EMICategoryName");
		// Add mappings for other transaction tables as needed

		Map<String, String> categoryIDMap = new HashMap<>();
		categoryIDMap.put("Income", "IncomeCategoryID");
		categoryIDMap.put("Expenses", "ExpenseCategoryID");
		categoryIDMap.put("Investments", "InvestmentCategoryID");
		categoryIDMap.put("Debts", "DebtCategoryID");
		categoryIDMap.put("SIP", "SIPCategoryID");
		categoryIDMap.put("EMI", "EMICategoryID");

		Map<String, String> IDMap = new HashMap<>();
		IDMap.put("Income", "IncomeID");
		IDMap.put("Expenses", "ExpenseID");
		IDMap.put("Investments", "InvestmentID");
		IDMap.put("Debts", "DebtID");
		IDMap.put("SIP", "SIPID");
		IDMap.put("EMI", "EMIID");
		// Get the name of the category table associated with the transaction table
		// ------------------------------------------------------------------------
		String categoryTableName = categoryTableMap.get(tableName);
		System.out.println("Category table Name : " + categoryTableName);

		// Get the field name for category name in the category table
		String categoryNameField = categoryNameFieldMap.get(tableName);
		System.out.println("Category Field Name : " + categoryNameField);

		String categoryID = categoryIDMap.get(tableName);
		System.out.println(categoryID);

		String ID = IDMap.get(tableName);
		System.out.println(ID);
		// -------------------------------------------------------------------------------------------------
		if (categoryTableName != null && categoryNameField != null && categoryID != null && ID != null) {
			// Use the dynamically determined category table name in the query
			String query = "SELECT t.*, c." + categoryNameField + " AS CategoryName " + "FROM " + tableName + " t "
					+ "LEFT JOIN " + categoryTableName + " c ON t." + categoryID + " = c." + categoryID + ""
					+ " WHERE t.UserID = " +UserID+ " ORDER BY t." + ID + " DESC";
			System.out.println("this USER ID from transctionDao :" + UserID);

			try (PreparedStatement statement = connection.prepareStatement(query);
					ResultSet resultSet = statement.executeQuery()) {

				while (resultSet.next()) {
					Transaction transaction = new Transaction();
					if ("Income".equals(tableName)) {
						transaction.setId(resultSet.getInt("IncomeID"));
						transaction.setDate(resultSet.getString("Date"));
						transaction.setAmount(resultSet.getDouble("Amount"));
						transaction.setCategory(resultSet.getString("CategoryName"));
						transaction.setMethod(resultSet.getString("Source"));
						transaction.setStatus(resultSet.getString("Status")); // Added line to set the Status attribute

						transaction.setDescription(resultSet.getString("Description"));
					} else if ("Expenses".equals(tableName)) {
						transaction.setId(resultSet.getInt("ExpenseID"));
						transaction.setDate(resultSet.getString("Date"));
						transaction.setAmount(resultSet.getDouble("Amount"));
						transaction.setCategory(resultSet.getString("CategoryName"));
						transaction.setMethod(resultSet.getString("PaymentMode"));
						transaction.setStatus(resultSet.getString("Status")); // Added line to set the Status attribute

						transaction.setDescription(resultSet.getString("Description"));
					} else if ("Investments".equals(tableName)) {
						transaction.setId(resultSet.getInt("InvestmentID"));
						transaction.setDate(resultSet.getString("PurchaseDate"));
						transaction.setAmount(resultSet.getDouble("Amount"));
						transaction.setCategory(resultSet.getString("CategoryName"));
						transaction.setMethod(resultSet.getString("PaymentMode"));
						transaction.setStatus(resultSet.getString("Status")); // Added line to set the Status attribute

						transaction.setDescription(resultSet.getString("Description"));
					} else if ("Debts".equals(tableName)) {
						transaction.setId(resultSet.getInt("DebtID"));
						transaction.setDate(resultSet.getString("PaymentSchedule"));
						transaction.setAmount(resultSet.getDouble("Amount"));
						transaction.setCategory(resultSet.getString("CategoryName"));
						transaction.setMethod(resultSet.getString("PaymentMode"));
						transaction.setStatus(resultSet.getString("Status")); // Added line to set the Status attribute

						transaction.setDescription(resultSet.getString("Description"));
					} else if ("SIP".equals(tableName)) {
						transaction.setId(resultSet.getInt("SIPID"));
						transaction.setDate(resultSet.getString("StartDate"));
						transaction.setAmount(resultSet.getDouble("Amount"));
						transaction.setCategory(resultSet.getString("CategoryName"));
						transaction.setMethod(resultSet.getString("PaymentMode"));
						transaction.setStatus(resultSet.getString("Status")); // Added line to set the Status attribute

						transaction.setDescription(resultSet.getString("Description"));
					} else if ("EMI".equals(tableName)) {
						transaction.setId(resultSet.getInt("EMIID"));
						transaction.setDate(resultSet.getString("PaymentDate"));
						transaction.setAmount(resultSet.getDouble("Amount"));
						transaction.setCategory(resultSet.getString("CategoryName"));
						transaction.setMethod(resultSet.getString("PaymentMethod"));
						transaction.setStatus(resultSet.getString("Status")); // Added line to set the Status attribute
						transaction.setDescription(resultSet.getString("Notes"));
					}

					transactions.add(transaction);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return transactions;
	}
}
