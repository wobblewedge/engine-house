package com.flow.enginehouse.entity;


public class Applicant{
	 	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(int loanAmount) {
		this.loanAmount = loanAmount;
	}
	public int getIncome() {
		return income;
	}
	public void setIncome(int income) {
		this.income = income;
	}
	public int getSSN() {
		return SSN;
	}
	public void setSSN(int sSN) {
		SSN = sSN;
	}
	public int getCreditScore() {
		return creditScore;
	}
	public void setCreditScore(int creditScore) {
		this.creditScore = creditScore;
	}
	public Applicant(String userId, String firstName, String lastName, String address, String email, int age,
			int loanAmount, int income, int sSN, int creditScore) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.email = email;
		this.age = age;
		this.loanAmount = loanAmount;
		this.income = income;
		SSN = sSN;
		this.creditScore = creditScore;
	}
	private String userId;
	private String firstName;
	private String lastName;
	private String address;
	private String email;
	private int age;
	private int loanAmount;
	private int income;
	private int SSN;
	private int creditScore;
	
}
	 
	