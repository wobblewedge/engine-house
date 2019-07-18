package com.flow.enginehouse.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Applicant {
	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String fullName;
	private String address;
	private int age;
	private int income;
	private int debts;
	private int assets;
	private int credit;
	private boolean approval;

	
	public boolean isApproval() {
		return approval;
	}

	public void setApproval(boolean approval) {
		this.approval = approval;
	}
	
	public Applicant() {}

	public Applicant(Long id, String fullName, String address, int age, int income, int debts, int assets,
			int credit) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.address = address;
		this.age = age;
		this.income = income;
		this.debts = debts;
		this.assets = assets;
		this.credit = credit;
		this.approval = false;
	}
	
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getIncome() {
		return income;
	}

	public void setIncome(int income) {
		this.income = income;
	}

	public int getDebts() {
		return debts;
	}

	public void setDebts(int debts) {
		this.debts = debts;
	}

	public int getAssets() {
		return assets;
	}

	public void setAssets(int assets) {
		this.assets = assets;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
