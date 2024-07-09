package com.exterro.grocerymgt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exterro.grocerymgt.model.GroceryItem;

/*
Author name : vimalraj.vijayakumar
Creation Date : 21-June-2024
*/

public interface GroceryRepo extends JpaRepository<GroceryItem, Integer>{
	Optional<GroceryItem> findByItemId(String itemId);
}
