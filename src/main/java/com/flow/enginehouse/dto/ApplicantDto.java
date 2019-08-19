package com.flow.enginehouse.dto;

public class ApplicantDto {
	
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
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Integer getSSN() {
		return SSN;
	}
	public void setSSN(Integer sSN) {
		SSN = sSN;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getIncome() {
		return income;
	}
	public void setIncome(Integer income) {
		this.income = income;
	}
	public Integer getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(Integer loanAmount) {
		this.loanAmount = loanAmount;
	}
	public Integer getCreditScore() {
		return creditScore;
	}
	public void setCreditScore(Integer creditScore) {
		this.creditScore = creditScore;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	String firstName;
	String lastName;
	Long userId;
	Integer SSN;
	String address;
	Integer age;
	Integer income;
	Integer loanAmount;
	Integer creditScore;
	String email;
	public ApplicantDto() {};
	public ApplicantDto(String firstName, String lastName, Integer SSN, Integer age, Integer income,
			Integer loanAmount, Integer creditScore, String email,String address) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.SSN = SSN;
		this.age = age;
		this.income = income;
		this.loanAmount = loanAmount;
		this.creditScore = creditScore;
		this.email = email;
		this.address = address;
	}
}