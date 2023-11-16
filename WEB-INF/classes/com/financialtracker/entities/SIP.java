package com.financialtracker.entities;

import java.sql.Date;

public class SIP {
    private int SIPID;
    private int UserID;
    private String FundName;
    private double Amount;
    private String StartDate;
    private String Frequency;
    private int SIPCategoryID;
    private String paymentMode;
    private String Description;
    private String SIPcategoryName;
    private int totalSIP;
    
    public SIP() {
        // Default no-argument constructor
    }
    
    public SIP(int totalsip) {
        // Default no-argument constructor
    	this.totalSIP = totalsip;
    }
    
    public SIP(String categoryName) {
    	this.SIPcategoryName = categoryName;
        // Default no-argument constructor
    }

    public SIP(int SIPID, int UserID, String FundName, double Amount, String StartDate, String Frequency, int SIPCategoryID, String paymentMode) {
        this.SIPID = SIPID;
        this.UserID = UserID;
        this.FundName = FundName;
        this.Amount = Amount;
        this.StartDate = StartDate;
        this.Frequency = Frequency;
        this.SIPCategoryID = SIPCategoryID;
        this.paymentMode = paymentMode;
    }
    
    public SIP(int UserID, String FundName, double Amount, String StartDate, String Frequency, int SIPCategoryID, String paymentMode,String description) {
        this.UserID = UserID;
        this.FundName = FundName;
        this.Amount = Amount;
        this.StartDate = StartDate;
        this.Frequency = Frequency;
        this.SIPCategoryID = SIPCategoryID;
        this.paymentMode = paymentMode;
        this.Description = description;
    }

    public SIP(int sipID2, String startDate2, double amount2, int categoryID, String fundName2, String frequency2,
			String paymentMode2, String description2) {
    	 this.SIPID = sipID2;
         this.FundName = fundName2;
         this.Amount = amount2;
         this.StartDate = startDate2;
         this.Frequency = frequency2;
         this.SIPCategoryID = categoryID;
         this.paymentMode = paymentMode2;
         this.Description = description2;
		// TODO Auto-generated constructor stub
	}
	public int getSIPID() {
        return SIPID;
    }

    public void setSIPID(int SIPID) {
        this.SIPID = SIPID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public String getFundName() {
        return FundName;
    }

    public void setFundName(String FundName) {
        this.FundName = FundName;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double Amount) {
        this.Amount = Amount;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String StartDate) {
        this.StartDate = StartDate;
    }

    public String getFrequency() {
        return Frequency;
    }

    public void setFrequency(String Frequency) {
        this.Frequency = Frequency;
    }

    public int getSIPCategoryID() {
        return SIPCategoryID;
    }

    public void setSIPCategoryID(int SIPCategoryID) {
        this.SIPCategoryID = SIPCategoryID;
    }
    
    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

	public String getSIPcategoryName() {
		return SIPcategoryName;
	}

	public void setSIPcategoryName(String sIPcategoryName) {
		SIPcategoryName = sIPcategoryName;
	}
	public void setStartDate(Date date) {
		// TODO Auto-generated method stub
		
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public int getTotalSIP() {
		return totalSIP;
	}
	public void setTotalSIP(int totalSIP) {
		this.totalSIP = totalSIP;
	}
}
