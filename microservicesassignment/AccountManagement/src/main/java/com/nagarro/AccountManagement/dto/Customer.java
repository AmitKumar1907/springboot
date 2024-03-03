package com.nagarro.AccountManagement.dto;

public class Customer {
	private int id;
	private int customerId;
	private String name;
	private int contact;
	private String accountNumber;
	private String email;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getContact() {
		return contact;
	}

	public void setContact(int contact) {
		this.contact = contact;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(int id, int customerId, String name, int contact, String accountNumber, String email) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.name = name;
		this.contact = contact;
		this.accountNumber = accountNumber;
		this.email = email;
	}

}
