package com.exterro.grocerymgt.dto;

/*
Author name : vimalraj.vijayakumar
Creation Date : 29-Jun-2024
*/

public class PasswordDto {
	private String customerMailId;
	private String customerOtp;

	public PasswordDto() {
	}

	public PasswordDto(String customerMailId, String customerOtp) {
		super();
		this.customerMailId = customerMailId;
		this.customerOtp = customerOtp;
	}

	public String getCustomerMailId() {
		return customerMailId;
	}

	public void setCustomerMailId(String customerMailId) {
		this.customerMailId = customerMailId;
	}

	public String getCustomerOtp() {
		return customerOtp;
	}

	public void setCustomerOtp(String customerOtp) {
		this.customerOtp = customerOtp;
	}

	@Override
	public String toString() {
		return "PasswordDto [customerMailId=" + customerMailId + ", customerOtp=" + customerOtp + "]";
	}
	
	
}
