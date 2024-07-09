package com.exterro.grocerymgt.model;

import jakarta.persistence.Embeddable;

/*
Author name : vimalraj.vijayakumar
Creation Date : 24-Jun-2024
*/
@Embeddable
public class CartItem {
	private String itemId;
	private int quantity;
	private int totalPrice;

	public CartItem() {
		super();
	}

	public CartItem(String itemId, int quantity, int totalPrice) {
		super();
		this.itemId = itemId;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
	}	

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
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
		return "CartItem [itemId=" + itemId + ", quantity=" + quantity + ", totalPrice=" + totalPrice + "]";
	}

}
