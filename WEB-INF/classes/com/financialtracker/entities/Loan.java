package com.financialtracker.entities;

import java.math.BigDecimal;

public class Loan {
	   private int debtID;
	    private int userID;
	    private String date;
	    private String creditor;
	    private double amount;
	    private double interestRate;
	    private String paymentSchedule;
	    private int debtCategoryID;
	    private String LoanCategoryName;
	    private String description;
	    private String paymentMode;
        private int totalLoan;
//	    private String debtName; // Add the debtName field

   public Loan(int totalAmount) {
	   this.totalLoan = totalAmount;
   }

    public Loan() {
        // Default constructor
    }

    public Loan(int userID, String creditor, String date,double amount, double interestRate, String paymentSchedule,String description , int debtCategoryID,String paymentMode) {
        this.userID = userID;
        this.creditor = creditor;
        this.date = date;
        this.amount = amount;
        this.interestRate = interestRate;
        this.paymentSchedule = paymentSchedule;
        this.debtCategoryID = debtCategoryID;
        this.setDescription(description);
        this.paymentMode = paymentMode;
//        this.debtName = debtName; // Initialize the debtName field
    }
    public Loan(int userID, String creditor,String date, double amount, double interestRate, String paymentSchedule, int debtCategoryID,String categoryName ) {
        this.userID = userID;
        this.creditor = creditor;
        this.date = date;
        this.amount = amount;
        this.interestRate = interestRate;
        this.paymentSchedule = paymentSchedule;
        this.debtCategoryID = debtCategoryID;
        this.LoanCategoryName = categoryName;
//        this.debtName = debtName; // Initialize the debtName field
    }

    public Loan(int loanId, String date2, double amount2, double interestRate2, int category,
			String paymentSchedule2, String creditor2, String paymentMode2, String description2) {
    	this.debtID = loanId;
    	this.paymentSchedule = date;
    	this.amount = amount2;
    	this.interestRate = interestRate2;
    	this.debtCategoryID = category;
    	this.paymentSchedule = paymentSchedule2;
    	this.creditor = creditor2;
    	this.paymentMode = paymentMode2;
    	this.description = description2;
    	this.date = date2;
		// TODO Auto-generated constructor stub
	}

	public Loan(Integer userID2, String creditor2, String date2, double amount2, double interestRate2,
			String paymentSchedule2, String description2, int categoryID, String paymentMode2) {
		// TODO Auto-generated constructor stub
		this.userID = userID2;
        this.creditor = creditor2;
        this.date = date2;
        this.amount = amount2;
        this.interestRate = interestRate2;
        this.paymentSchedule = paymentSchedule2;
        this.description = description2;
        this.debtCategoryID = categoryID;
        this.paymentMode = paymentMode2;
        
        
        
	}

	// Getters and setters for the fields
    public int getDebtID() {
        return debtID;
    }

    public void setDebtID(int debtID) {
        this.debtID = debtID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getCreditor() {
        return creditor;
    }

    public void setCreditor(String creditor) {
        this.creditor = creditor;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public String getPaymentSchedule() {
        return paymentSchedule;
    }

    public void setPaymentSchedule(String paymentSchedule) {
        this.paymentSchedule = paymentSchedule;
    }

    public int getDebtCategoryID() {
        return debtCategoryID;
    }

    public void setDebtCategoryID(int debtCategoryID) {
        this.debtCategoryID = debtCategoryID;
    }

	public String getLoanCategoryName() {
		return LoanCategoryName;
	}

	public void setLoanCategoryName(String loanCategoryName) {
		LoanCategoryName = loanCategoryName;
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
		this.date = date;
		System.out.println("loan class date :"+date);
	}

	public int getTotalLoan() {
		return totalLoan;
	}

	public void setTotalLoan(int totalLoan) {
		this.totalLoan = totalLoan;
	}

//    public String getDebtName() {
//        return debtName;
//    }
//
//    public void setDebtName(String debtName) {
//        this.debtName = debtName;
//    }
}
