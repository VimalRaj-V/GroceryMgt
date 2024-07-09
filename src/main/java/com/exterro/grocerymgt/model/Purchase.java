package com.exterro.grocerymgt.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/*
Author name : vimalraj.vijayakumar
Creation Date : 21-Jun-2024
*/
@Entity
public class Purchase {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String customerId;
	@ElementCollection
	private List<CartItem> itemList = new ArrayList<>();

	public Purchase() {
		super();
	}

	public Purchase(int id, String customerId, List<CartItem> itemList) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.itemList = itemList;
	}




	public List<CartItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<CartItem> itemList) {
		this.itemList = itemList;
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

	@Override
	public String toString() {
		return "Purchase [id=" + id + ", customerId=" + customerId + "]";
	}

}
