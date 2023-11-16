package com.financialtracker.entities;



public class Expense {
    private int expenseID;
    private int userID;
    private String description;
    private double amount;
    private String date;
    private boolean recurring;
    private int expenseCategoryID;
    private String ExpenseCategoryName;
    private String PaymentMode;
    private int totalAmount;
    
    public Expense(int totalAmount) {
    	this.totalAmount = totalAmount;
    }

    public Expense(int expenseID, int userID, String description, double amount, String date, boolean recurring, int expenseCategoryID) {
        this.expenseID = expenseID;
        this.userID = userID;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.recurring = recurring;
        this.expenseCategoryID = expenseCategoryID;
    }

    public Expense(int userID, String description, double amount, String date,String paymentMode, int expenseCategoryID) {
        this.userID = userID;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.PaymentMode = paymentMode;
//        this.recurring = recurring;
        this.expenseCategoryID = expenseCategoryID;
    }
    
    public Expense(int userID, String description, String categoryName, double amount, String date, int expenseCategoryID) {
        this.userID = userID;
        this.description = description;
        this.ExpenseCategoryName = categoryName;
        this.amount = amount;
        this.date = date;
//        this.recurring = recurring;
        this.expenseCategoryID = expenseCategoryID;
    }

	public Expense() {
		// TODO Auto-generated constructor stub
	}

	public int getExpenseID() {
        return expenseID;
    }

    public void setExpenseID(int expenseID) {
        this.expenseID = expenseID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isRecurring() {
        return recurring;
    }

    public void setRecurring(boolean recurring) {
        this.recurring = recurring;
    }

    public int getExpenseCategoryID() {
        return expenseCategoryID;
    }

    public void setExpenseCategoryID(int expenseCategoryID) {
        this.expenseCategoryID = expenseCategoryID;
    }

	public String getExpenseCategoryName() {
		return ExpenseCategoryName;
	}

	public void setExpenseCategoryName(String expenseCategoryName) {
		ExpenseCategoryName = expenseCategoryName;
	}

	public String getPaymentMode() {
		return PaymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		PaymentMode = paymentMode;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
}
