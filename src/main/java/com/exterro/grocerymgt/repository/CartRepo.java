package com.exterro.grocerymgt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exterro.grocerymgt.model.Cart;
import com.exterro.grocerymgt.model.Customer;

/*
Author name : vimalraj.vijayakumar
Creation Date : 21-June-2024
*/

public interface CartRepo extends JpaRepository<Cart, Integer>{
	Optional<Cart> findByCustomer(Customer customer);
}