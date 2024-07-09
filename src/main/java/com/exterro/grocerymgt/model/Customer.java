package com.exterro.grocerymgt.model;

import com.exterro.grocerymgt.dto.CustomerDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/*
Author name : vimalraj.vijayakumar
Creation Date : 21-Jun-2024
*/

@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String customerId;
	@Column(unique = true)
	private String customerUserName;
	private String customerName;
	private String customerPassword;
	private String customerMailId;
	private String customerOtp;
	

	public Customer() {
		super();
	}
	
	public Customer(int id, String customerId, String customerUserName, String customerName, String customerPassword,
			String customerMailId) {
		this.id = id;
		this.customerId = customerId;
		this.customerUserName = customerUserName;
		this.customerName = customerName;
		this.customerPassword = customerPassword;
		this.customerMailId = customerMailId;
	}
	public Customer(CustomerDto customerDto) {
		this.setCustomerName(customerDto.getCustomerName());
		this.setCustomerUserName(customerDto.getCustomerUserName());
		this.setCustomerPassword(customerDto.getCustomerPassword());
		this.setCustomerMailId(customerDto.getCustomerMailId());
		
	}
	
	
	
	public String getCustomerOtp() {
		return customerOtp;
	}

	public void setCustomerOtp(String customerOtp) {
		this.customerOtp = customerOtp;
	}

	public Customer(int id, String customerId, String customerUserName, String customerName, String customerPassword,
			String customerMailId, String customerOtp) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.customerUserName = customerUserName;
		this.customerName = customerName;
		this.customerPassword = customerPassword;
		this.customerMailId = customerMailId;
		this.customerOtp = customerOtp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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
		return "Customer [customerId=" + customerId + ", customerUserName=" + customerUserName + ", customerName="
				+ customerName + ", customerMailId=" + customerMailId + "]";
	}
}
