package com.exterro.grocerymgt.dto;


/*
Author name : vimalraj.vijayakumar
Creation Date : 27-June-2024
*/

public class CartItemDto {
	private String itemId;
	private String itemName;
	private float itemPrice;
	private String itemBrand;
	private int quantity;
	private int totalPrice;

	public CartItemDto() {
		super();
	}

	public CartItemDto(String itemId, String itemName, float itemPrice, String itemBrand, int quantity,
			int totalPrice) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.itemBrand = itemBrand;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
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

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "CartItemDto [itemId=" + itemId + ", itemName=" + itemName + ", itemPrice=" + itemPrice + ", itemBrand="
				+ itemBrand + ", quantity=" + quantity + ", totalPrice=" + totalPrice + "]";
	}

}
