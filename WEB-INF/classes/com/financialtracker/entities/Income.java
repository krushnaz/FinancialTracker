package com.financialtracker.entities;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
public class Income {
    private int incomeID;
    private int userID;
    private String source;
    private double amount;
    private String date;
    private int categoryID;
    private String categoryName;
    private boolean recurring;
    private String description;
    // Constructor without parameters
    
    private int totalAmount;
    public Income(int totalAmount) {
    	this.totalAmount = totalAmount;
    }
    public Income() {
    }

    // Constructor with parameters
    public Income(int incomeID, int userID, String source, double amount, String date, int categoryID, boolean recurring,String description) {
        this.incomeID = incomeID;
        this.userID = userID;
        this.source = source;
        this.amount = amount;
        this.date = date;
        this.categoryID = categoryID;
        this.recurring = recurring;
        this.description = description;
    }
    
    public Income( int userID, String source, double amount, String date, int categoryID,String description) {
//        this.incomeID = incomeID;
        this.userID = userID;
        this.source = source;
        this.amount = amount;
        this.date = date;
        this.categoryID = categoryID;
        this.description = description;
    }

    public Income( int userID, String source, double amount,String categoryName, String date, int categoryID,String description) {
      this.userID = userID;
      this.source = source;
      this.amount = amount;
      this.date = date;
      this.setCategoryName(categoryName);
      this.categoryID = categoryID;
      this.description = description;
  }


    // Getter and Setter methods for IncomeID
    public int getIncomeID() {
        return incomeID;
    }

    public void setIncomeID(int incomeID) {
        this.incomeID = incomeID;
    }

    // Getter and Setter methods for UserID
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    // Getter and Setter methods for Source
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    // Getter and Setter methods for Amount
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    // Getter and Setter methods for Date
	/*
	 * public String getDate() {
	 * 
	 * return date; }
	 */
    
    public String getDate() {
    return date;
    }



    public void setDate(String date) {
        this.date = date;
    }

    // Getter and Setter methods for CategoryID
    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    // Getter and Setter methods for Recurring
    public boolean isRecurring() {
        return recurring;
    }

    public void setRecurring(boolean recurring) {
        this.recurring = recurring;
    }

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

    // You can add toString() or other methods as needed
}
