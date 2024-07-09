package com.exterro.grocerymgt.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

/*
Author name : vimalraj.vijayakumar
Creation Date : 21-Jun-2024
*/

@Entity
public class Cart {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@OneToOne
	private Customer customer;
	@ElementCollection
	private List<CartItem> itemList = new ArrayList<>();

	public Cart() {
	
	}

	public Cart(Customer customer) {
		super();
		this.customer = customer;
	}

	public Cart(int id, Customer customer, List<CartItem> itemList) {
		super();
		this.id = id;
		this.customer = customer;
		this.itemList = itemList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<CartItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<CartItem> itemList) {
		this.itemList = itemList;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", customer=" + customer + ", itemList=" + itemList + "]";
	}

}
