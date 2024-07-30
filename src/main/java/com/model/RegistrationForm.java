package com.model;

import java.math.BigDecimal;

public class RegistrationForm {

	private String firstName;
	private String lastName;
	private String dob;
	private String email;
	private String phone;
	private String address;
	private String accountType;
	private String branch;
	private String accountNumber;
	private String ifsc;
	private String nominee;
	private BigDecimal amount;
	private String pin;

	public RegistrationForm() {
	}

	public RegistrationForm(String firstName, String lastName, String dob, String email, String phone, String address,
			String accountType, String branch, String accountNumber, String ifsc, String nominee, BigDecimal amount, String pin) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.accountType = accountType;
		this.branch = branch;
		this.accountNumber = accountNumber;
		this.ifsc = ifsc;
		this.nominee = nominee;
		this.amount = amount;
		this.pin = pin;
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

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	public String getNominee() {
		return nominee;
	}

	public void setNominee(String nominee) {
		this.nominee = nominee;
	}
	
	  public BigDecimal getAmount() {
	        return amount;
	    }

	    public void setAmount(BigDecimal amount) {
	        this.amount = amount;
	    }
	    
	    public String getPin() {
	        return pin;
	    }

	    public void setPin(String pin) {
	        this.pin = pin;
	    }

		

		
}
