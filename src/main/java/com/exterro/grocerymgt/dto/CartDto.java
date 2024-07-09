package com.exterro.grocerymgt.dto;

import java.util.ArrayList;
import java.util.List;

import com.exterro.grocerymgt.model.CartItem;

/*
Author name : vimalraj.vijayakumar
Creation Date : 26-June-2024
*/

public class CartDto {
	private String customerUserName;
	private List<CartItem> itemList = new ArrayList<>();

	public CartDto() {
		super();
	}

	public CartDto(String customerUserName, List<CartItem> itemList) {
		super();
		this.customerUserName = customerUserName;
		this.itemList = itemList;
	}

	public String getCustomerUserName() {
		return customerUserName;
	}

	public void setCustomerUserName(String customerUserName) {
		this.customerUserName = customerUserName;
	}

	public List<CartItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<CartItem> itemList) {
		this.itemList = itemList;
	}

	@Override
	public String toString() {
		return "CartDto [customerUserName=" + customerUserName + ", itemList=" + itemList + "]";
	}

}
