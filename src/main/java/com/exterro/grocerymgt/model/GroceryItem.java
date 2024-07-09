package com.exterro.grocerymgt.model;

import com.exterro.grocerymgt.dto.GroceryDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/*
Author name : vimalraj.vijayakumar
Creation Date : 21-Jun-2024
*/

@Entity
public class GroceryItem {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int id;
	private String itemId;
	private String itemName;
	private float itemPrice;
	private String itemBrand;
	
	public GroceryItem() {
		super();
	}
	
	public GroceryItem(int id, String itemId, String itemName, float itemPrice, String itemBrand) {
		super();
		this.id = id; 
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.itemBrand = itemBrand;
	}
	
	public GroceryItem(int id, GroceryDto groceryDto) {
		this.id = id; 
		this.itemId = "G"+Integer.toString(id);
		this.itemName = groceryDto.getItemName();
		this.itemPrice = groceryDto.getItemPrice();
		this.itemBrand = groceryDto.getItemBrand();
	}
	
	public GroceryItem(GroceryDto groceryDto) {
		this.itemName = groceryDto.getItemName();
		this.itemPrice = groceryDto.getItemPrice();
		this.itemBrand = groceryDto.getItemBrand();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
	@Override
	public String toString() {
		return "GroceryItem [itemId=" + itemId + ", itemName=" + itemName + ", itemPrice=" + itemPrice + ", itemBrand="
				+ itemBrand + "]";
	}
	
}
