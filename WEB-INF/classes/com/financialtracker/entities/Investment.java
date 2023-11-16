package com.financialtracker.entities;

import java.math.BigDecimal;
import java.util.Date;

public class Investment {
	private int investmentID;
	private int userID;
	private String date;
	private int investmentCategoryID;
	private String purchaseDate;
	private BigDecimal purchasePrice;
	private BigDecimal quantity;
	private BigDecimal amount;
	private String description;
	private String paymentMode;
	private String investmentCategoryName;
	private int totalAmount;
	
	public Investment(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Investment(int investmentID2, String date2, BigDecimal amount2, int categoryID, String incomeMode, String description2, String purchaseDate2) {
		// Default constructor
		this.investmentID = investmentID2;
		this.amount = amount2;
		this.date = date2;
		this.investmentCategoryID = categoryID;
		this.paymentMode = incomeMode;
		this.purchaseDate = purchaseDate2;
		this.description = description2;
		
	}

	public Investment(int userID, int investmentCategoryID, String purchaseDate, BigDecimal purchasePrice,
			BigDecimal quantity, BigDecimal amount, String description, String paymentMode) {
		this.userID = userID;
		this.investmentCategoryID = investmentCategoryID;
		this.purchaseDate = purchaseDate;
		this.purchasePrice = purchasePrice;
		this.quantity = quantity;
		this.amount = amount;
		this.description = description;
		this.paymentMode = paymentMode;
	}

	public Investment(Integer userID2, double amount2, String date, int categoryID, String incomeMode,
			String purchaseDate2, String description2) {
		this.userID = userID2;
		this.amount = new BigDecimal(amount2);
		this.setDate(date);
		this.investmentCategoryID = categoryID;
		this.paymentMode = incomeMode;
		this.purchaseDate = purchaseDate2;
		this.description = description2;
	}

	public Investment(Integer userID2, double amount2, String date, int categoryID, String incomeMode,
			String purchaseDate2, String description2, String categoryName) {
		this.userID = userID2;
		this.amount = new BigDecimal(amount2);
		this.setDate(date);
		this.investmentCategoryID = categoryID;
		this.paymentMode = incomeMode;
		this.purchaseDate = purchaseDate2;
		this.description = description2;
		this.investmentCategoryName = categoryName;
	}

	

//	public Investment(int investmentID2, String date2, int amount2, int categoryID, String incomeMode,
//			String description2, String purchaseDate2) {
//		this.investmentID = investmentID2;
//		this.amount = new BigDecimal(amount2);
//		this.setDate(date);
//		this.investmentCategoryID = categoryID;
//		this.paymentMode = incomeMode;
//		this.purchaseDate = purchaseDate2;
//		this.description = description2;
//		// TODO Auto-generated constructor stub
//	}

	public Investment() {
		// TODO Auto-generated constructor stub
	}

	public int getInvestmentID() {
		return investmentID;
	}

	public void setInvestmentID(int investmentID) {
		this.investmentID = investmentID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getInvestmentCategoryID() {
		return investmentCategoryID;
	}

	public void setInvestmentCategoryID(int investmentCategoryID) {
		this.investmentCategoryID = investmentCategoryID;
	}

	public String getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public BigDecimal getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
//		System.out.println("investment class setDate method : "+date);
		this.date = date;
	}

	public String getInvestmentCategoryName() {
		return investmentCategoryName;
	}

	public void setInvestmentCategoryName(String investmentCategoryName) {
		this.investmentCategoryName = investmentCategoryName;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
}
