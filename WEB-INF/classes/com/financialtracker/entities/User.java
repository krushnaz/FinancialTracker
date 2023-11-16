package com.financialtracker.entities;
import java.sql.Date;
public class User {
	private int userId;
	private String firstName;
	private String lastName;
	private String email;
//	private Date DOB;
	private long phoneNumber;
	private String profilePicture;
	private String gender;
	private String password;
	

	public User( int userId,
     String firstName,
	 String lastName,
	 String email,
//	 Date DOB,
	 long phoneNumber,
	 String profilePicture,
	 String gender,
	 String password) {
		// TODO Auto-generated constructor stub
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
//		this.DOB = DOB;
		this.phoneNumber = phoneNumber;
		this.profilePicture = profilePicture;
		this.gender = gender;
		this.password = password;
	}
	
	public User( 
		     String firstName,
			 String lastName,
			 String email,
//			 Date DOB,
			 long phoneNumber,
			 String profilePicture,
			 String gender,
			 String password) {
				// TODO Auto-generated constructor stub
			
				this.firstName = firstName;
				this.lastName = lastName;
				this.email = email;
//				this.DOB = DOB;
				this.phoneNumber = phoneNumber;
				this.profilePicture = profilePicture;
				this.gender = gender;
				this.password = password;
			}


	public User() {
		// TODO Auto-generated constructor stub
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

//	public Date getDOB() {
//		return DOB;
//	}

//	public void setDOB(Date dOB) {
//		DOB = dOB;
//	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
