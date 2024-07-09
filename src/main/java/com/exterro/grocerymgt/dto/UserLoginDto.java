package com.exterro.grocerymgt.dto;

/*
Author name : vimalraj.vijayakumar
Creation Date : 24-June-2024
*/

public class UserLoginDto {
	private String customerUserName;
	private String customerPassword;

	public UserLoginDto() {
		super();
	}

	public UserLoginDto(String customerUserName, String customerPassword) {
		super();
		this.customerUserName = customerUserName;
		this.customerPassword = customerPassword;
	}

	public String getCustomerUserName() {
		return customerUserName;
	}

	public void setCustomerUserName(String customerUserName) {
		this.customerUserName = customerUserName;
	}

	public String getCustomerPassword() {
		return customerPassword;
	}

	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}

	@Override
	public String toString() {
		return "UserLoginDto [customerUserName=" + customerUserName + ", customerPassword=" + customerPassword + "]";
	}

}
