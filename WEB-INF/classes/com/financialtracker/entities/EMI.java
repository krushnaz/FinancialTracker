package com.financialtracker.entities;

public class EMI {
    private int EMIID;
    private int DebtID;
    private int UserID;
    private String PaymentDate;
    private int Amount;
    private String PaymentMethod;
    private String Notes;
    private int EMICategoryID;
    private String EMIcategoryName;
    private int totalEMI;
    // Default constructor
    public EMI() {
        // Default constructor with no arguments
    }
    
    public EMI(int totalemi) {
        this.totalEMI = totalemi;
    }

    public EMI(String categoryName) {
    		this.EMIcategoryName = categoryName;
    }
    // Constructor with all fields
    public EMI(int EMIID, int DebtID, int UserID, String PaymentDate, int Amount, String PaymentMethod, String Notes, int EMICategoryID) {
        this.EMIID = EMIID;
        this.DebtID = DebtID;
        this.UserID = UserID;
        this.PaymentDate = PaymentDate;
        this.Amount = Amount;
        this.PaymentMethod = PaymentMethod;
        this.Notes = Notes;
        this.EMICategoryID = EMICategoryID;
    }

    // Constructor without EMIID (used when inserting new records)
    public EMI(int DebtID, int UserID, String PaymentDate, int Amount, String PaymentMethod, String Notes, int EMICategoryID) {
        this.DebtID = DebtID;
        this.UserID = UserID;
        this.PaymentDate = PaymentDate;
        this.Amount = Amount;
        this.PaymentMethod = PaymentMethod;
        this.Notes = Notes;
        this.EMICategoryID = EMICategoryID;
    }

    public int getEMIID() {
        return EMIID;
    }

    public void setEMIID(int EMIID) {
        this.EMIID = EMIID;
    }

    public int getDebtID() {
        return DebtID;
    }

    public void setDebtID(int DebtID) {
        this.DebtID = DebtID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public String getPaymentDate() {
        return PaymentDate;
    }

    public void setPaymentDate(String PaymentDate) {
        this.PaymentDate = PaymentDate;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int Amount) {
        this.Amount = Amount;
    }

    public String getPaymentMethod() {
        return PaymentMethod;
    }

    public void setPaymentMethod(String PaymentMethod) {
        this.PaymentMethod = PaymentMethod;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String Notes) {
        this.Notes = Notes;
    }

    public int getEMICategoryID() {
        return EMICategoryID;
    }

    public void setEMICategoryID(int EMICategoryID) {
        this.EMICategoryID = EMICategoryID;
    }

	public String getEMIcategoryName() {
		return EMIcategoryName;
	}

	public void setEMIcategoryName(String eMIcategoryName) {
		EMIcategoryName = eMIcategoryName;
	}

	public int getTotalEMI() {
		return totalEMI;
	}

	public void setTotalEMI(int totalEMI) {
		this.totalEMI = totalEMI;
	}

	
}
