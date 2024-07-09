package com.exterro.grocerymgt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exterro.grocerymgt.model.Purchase;

/*
Author name : vimalraj.vijayakumar
Creation Date : 21-June-2024
*/

public interface PurchaseRepo extends JpaRepository<Purchase, Integer>, PurchaseCustomRepo{

}