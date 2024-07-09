package com.exterro.grocerymgt.dto;

import com.exterro.grocerymgt.model.Customer;

/*
Author name : vimalraj.vijayakumar
Creation Date : 02-July-2024
*/

public class CustomerPurchaseDto {
	private String customerUserName;
	private String customerName;
	private String customerMailId;
	private int purchasesMade;
	private float totalPurchasedAmount;
	private float avgPurchasedAmount;

	public CustomerPurchaseDto() {
		super();
	}
	
	public CustomerPurchaseDto(Customer customer, int purchasesMade,
			float totalPurchasedAmount) {
		this.customerUserName = customer.getCustomerUserName();
		this.customerName = customer.getCustomerName();
		this.customerMailId = customer.getCustomerMailId();
		this.purchasesMade = purchasesMade;
		this.totalPurchasedAmount = totalPurchasedAmount;
		this.avgPurchasedAmount = totalPurchasedAmount/purchasesMade;
	}

	public CustomerPurchaseDto(String customerUserName, String customerName, String customerMailId, int purchasesMade,
			float totalPurchasedAmount, float avgPurchasedAmount) {
		super();
		this.customerUserName = customerUserName;
		this.customerName = customerName;
		this.customerMailId = customerMailId;
		this.purchasesMade = purchasesMade;
		this.totalPurchasedAmount = totalPurchasedAmount;
		this.avgPurchasedAmount = avgPurchasedAmount;
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

	public String getCustomerMailId() {
		return customerMailId;
	}

	public void setCustomerMailId(String customerMailId) {
		this.customerMailId = customerMailId;
	}

	public int getPurchasesMade() {
		return purchasesMade;
	}

	public void setPurchasesMade(int purchasesMade) {
		this.purchasesMade = purchasesMade;
	}

	public float getTotalPurchasedAmount() {
		return totalPurchasedAmount;
	}

	public void setTotalPurchasedAmount(float totalPurchasedAmount) {
		this.totalPurchasedAmount = totalPurchasedAmount;
	}

	public float getAvgPurchasedAmount() {
		return avgPurchasedAmount;
	}

	public void setAvgPurchasedAmount(float avgPurchasedAmount) {
		this.avgPurchasedAmount = avgPurchasedAmount;
	}

	@Override
	public String toString() {
		return "CustomerPurchaseDto [customerUserName=" + customerUserName + ", customerName=" + customerName
				+ ", customerMailId=" + customerMailId + ", purchasesMade=" + purchasesMade + ", totalPurchasedAmount="
				+ totalPurchasedAmount + ", avgPurchasedAmount=" + avgPurchasedAmount + "]";
	}
	
	
}
