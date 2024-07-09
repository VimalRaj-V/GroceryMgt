package com.exterro.grocerymgt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exterro.grocerymgt.dto.CartDto;
import com.exterro.grocerymgt.dto.MessageDto;
import com.exterro.grocerymgt.service.CartService;

/*
Author name : vimalraj.vijayakumar
Creation Date : 26-June-2024
*/

@RestController
@CrossOrigin("http://localhost:4200/")
public class CartController {
	@Autowired
	private CartService cartSvc;
	
	@PutMapping("addtocart")
	ResponseEntity<MessageDto> addToCart(@RequestBody CartDto cartDto) {
		System.out.println(cartDto.getCustomerUserName());
		return ResponseEntity.ok(cartSvc.addToCart(cartDto));

	}
}

