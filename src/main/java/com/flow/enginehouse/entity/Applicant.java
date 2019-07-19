package com.flow.enginehouse.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Applicant implements Serializable{
	 
	private static final long serialVersionUID = 1L;
	  @Column
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  @Id
	private Long id;
	 @Column
	private String name;
	@Column
	private String address;
	 @Column
	private int age;
	 @Column
	private int income;
	 @Column
	private int debts;
	 @Column
	private int assets;
	 @Column
	private int credit;
	 @Column
	private boolean approval;
	 
	 public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isApproval() {
		return approval;
	}

	public void setApproval(boolean approval) {
		this.approval = approval;
	}
	
	public Applicant() {}

	public Applicant(String name, String address, int age, int income, int debts, int assets,
			int credit) {
		this.name = name;
		this.address = address;
		this.age = age;
		this.income = income;
		this.debts = debts;
		this.assets = assets;
		this.credit = credit;
		this.approval = false;
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
