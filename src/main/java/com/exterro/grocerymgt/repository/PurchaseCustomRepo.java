package com.exterro.grocerymgt.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.exterro.grocerymgt.model.Purchase;

/*
Author name : vimalraj.vijayakumar
Creation Date : 28-June-2024
*/

@Repository
public interface PurchaseCustomRepo {
	public List<Purchase> bestSellingProducts();
}
