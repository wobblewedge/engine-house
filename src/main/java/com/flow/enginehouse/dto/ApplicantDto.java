package com.flow.enginehouse.dto;

public class ApplicantDto {

	    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Integer getDebts() {
		return debts;
	}

	public void setDebts(Integer debts) {
		this.debts = debts;
	}

	public Integer getAssets() {
		return assets;
	}

	public void setAssets(Integer assets) {
		this.assets = assets;
	}

	public Integer getCredit() {
		return credit;
	}

	public void setCredit(Integer credit) {
		this.credit = credit;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isApproval() {
		return approval;
	}

	public void setApproval(boolean approval) {
		this.approval = approval;
	}

		Long id;
	    public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		String name;
	    Integer age;
	    Integer income;
	    Integer debts;
	    Integer assets;
	    Integer credit;
	    String address;
		boolean approval;

		public ApplicantDto(Long id, String name, String address, Integer age, Integer income, Integer debts, Integer assets,
				Integer credit) {
			this.id=id;
			this.name = name;
			this.address = address;
			this.age = age;
			this.income = income;
			this.debts = debts;
			this.assets = assets;
			this.credit = credit;
			this.approval = false;
		}

	    public ApplicantDto() {
	    }

	}


