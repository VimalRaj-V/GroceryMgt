package com.exterro.grocerymgt.service;

import org.springframework.stereotype.Service;

import com.exterro.grocerymgt.dto.CartDto;
import com.exterro.grocerymgt.dto.MessageDto;

/*
Author name : vimalraj.vijayakumar
Creation Date : 26-June-2024
*/

@Service
public interface CartService {
	public MessageDto addToCart(CartDto cartDto);
}
