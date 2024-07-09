package com.exterro.grocerymgt.dto;

import com.exterro.grocerymgt.model.GroceryItem;

/*
Author name : vimalraj.vijayakumar
Creation Date : 01-Jul-2024
*/

public class ProductPerformaceDto {
	private String itemId;
	private String itemName;
	private float itemPrice;
	private String itemBrand;
	private int quantitySold;
	private float revenueGenerated;

	public ProductPerformaceDto() {
	}
	
	public ProductPerformaceDto(GroceryItem groceryItem, int quantitySold) {
		super();
		this.itemId = groceryItem.getItemId();
		this.itemName = groceryItem.getItemName();
		this.itemPrice = groceryItem.getItemPrice();
		this.itemBrand = groceryItem.getItemBrand();
		this.quantitySold = quantitySold;
		this.revenueGenerated = this.quantitySold*this.itemPrice;
	}

	public ProductPerformaceDto(String itemId, String itemName, float itemPrice, String itemBrand, int quantitySold,
			float revenueGenerated) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.itemBrand = itemBrand;
		this.quantitySold = quantitySold;
		this.revenueGenerated = revenueGenerated;
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

	public int getQuantitySold() {
		return quantitySold;
	}

	public void setQuantitySold(int quantitySold) {
		this.quantitySold = quantitySold;
	}

	public float getRevenueGenerated() {
		return revenueGenerated;
	}

	public void setRevenueGenerated(float revenueGenerated) {
		this.revenueGenerated = revenueGenerated;
	}

	@Override
	public String toString() {
		return "ProductPerformaceDto [itemId=" + itemId + ", itemName=" + itemName + ", itemPrice=" + itemPrice
				+ ", itemBrand=" + itemBrand + ", quantitySold=" + quantitySold + ", revenueGenerated="
				+ revenueGenerated + "]";
	}
}
