package com.exterro.grocerymgt.dto;

import com.exterro.grocerymgt.model.GroceryItem;

/*
Author name : vimalraj.vijayakumar
Creation Date : 01-Jul-2024
*/

public class GroceryItemDto {
	private String itemId;
	private String itemName;
	private float itemPrice;
	private String itemBrand;
	private int quantity;
	
	public GroceryItemDto() {
	}
	
	public GroceryItemDto(GroceryItem groceryItem, int quantity) {
		super();
		this.itemId = groceryItem.getItemId();
		this.itemName = groceryItem.getItemName();
		this.itemPrice = groceryItem.getItemPrice();
		this.itemBrand = groceryItem.getItemBrand();
		this.quantity = quantity;
	} 

	public GroceryItemDto(String itemId, String itemName, float itemPrice, String itemBrand, int quantity) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.itemBrand = itemBrand;
		this.quantity = quantity;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "GroceryItemDto [itemId=" + itemId + ", itemName=" + itemName + ", itemPrice=" + itemPrice
				+ ", itemBrand=" + itemBrand + ", quantity=" + quantity + "]";
	}
	
	

}
