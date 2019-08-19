package com.flow.enginehouse.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.springframework.lang.Nullable;

@Entity
public class Applicant implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Long userId;
	@Column
	private String firstName;
	@Column
	private String lastName;
	@Column
	private Integer SSN;
	@Column
	private Integer age;
	@Column
	private Integer income;
	
	 @OneToMany(
				cascade = CascadeType.ALL,
			orphanRemoval=true)
	@JoinColumn(name="userId")
	private List<ApplicationProcess> applications = new ArrayList<>();

	public List<ApplicationProcess> getApplications() {
		return applications;
	}

	public void setApplications(List<ApplicationProcess> applications) {
		this.applications = applications;
	}
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Column
	private Integer loanAmount;
	@Column
	private Integer creditScore;
	@Column
	private String email;
@Column
private String address;

public Applicant() {}

public Applicant(String firstName, String lastName, Integer SSN, Integer age, Integer income, Integer loanAmount,
		Integer creditScore, String email, String address) {
	super();
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
