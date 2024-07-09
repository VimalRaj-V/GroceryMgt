package com.exterro.grocerymgt.dto;

/*
Author name : vimalraj.vijayakumar
Creation Date : 24-Jun-2024
*/

public class GroceryDto {
	private String itemName;
	private float itemPrice;
	private String itemBrand;

	public GroceryDto() {
		super();
	}

	public GroceryDto(String itemName, float itemPrice, String itemBrand) {
		super();
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.itemBrand = itemBrand;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public float getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(float itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getItemBrand() {
		return itemBrand;
	}

	public void setItemBrand(String itemBrand) {
		this.itemBrand = itemBrand;
	}

	@Override
	public String toString() {
		return "GroceryDto [itemName=" + itemName + ", itemPrice=" + itemPrice + ", itemBrand=" + itemBrand + "]";
	}

}
