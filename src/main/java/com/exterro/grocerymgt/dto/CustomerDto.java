package com.exterro.grocerymgt.dto;

import com.exterro.grocerymgt.model.Customer;

/*
Author name : vimalraj.vijayakumar
Creation Date : 21-Jun-2024
*/

public class CustomerDto {
	private String customerUserName;
	private String customerName;
	private String customerPassword;
	private String customerMailId;

	public CustomerDto() {
		super();
	}

	public CustomerDto(Customer customer) {
		this.customerUserName = customer.getCustomerUserName();
		this.customerName = customer.getCustomerName();
		this.customerMailId = customer.getCustomerMailId();
	}

	public CustomerDto(String customerUserName, String customerName, String customerPassword, String customerMailId) {
		super();
		this.customerUserName = customerUserName;
		this.customerName = customerName;
		this.customerPassword = customerPassword;
		this.customerMailId = customerMailId;
	}

	public String getCustomerUserName() {
		return customerUserName;
	}

	public void setCustomerUserName(String customerUserName) {
		this.customerUserName = customerUserName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPassword() {
		return customerPassword;
	}

	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}

	public String getCustomerMailId() {
		return customerMailId;
	}

	public void setCustomerMailId(String customerMailId) {
		this.customerMailId = customerMailId;
	}

	@Override
	public String toString() {
		return "CustomerDto [customerUserName=" + customerUserName + ", customerName=" + customerName
				+ ", customerMailId=" + customerMailId + "]";
	}

}
