package com.exterro.grocerymgt.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exterro.grocerymgt.dto.CartItemDto;
import com.exterro.grocerymgt.dto.MessageDto;
import com.exterro.grocerymgt.dto.ProductPerformaceDto;
import com.exterro.grocerymgt.repository.PurchaseCustomRepoImpl;
import com.exterro.grocerymgt.service.PurchaseService;

/*
Author name : vimalraj.vijayakumar
Creation Date : 26-June-2024
*/

@RestController
@CrossOrigin("http://localhost:4200/")
public class PurchaseController {
	@Autowired
	private PurchaseService purchaseSvc;
	
	@Autowired
	private PurchaseCustomRepoImpl PurchaseCustomRepoImpl;
	
	@PostMapping("viewCart")
	ResponseEntity<List<CartItemDto>> viewCart(@RequestBody String customerUserName) {
		return ResponseEntity.ok(purchaseSvc.viewCart(customerUserName));
	}
	
	@PostMapping("purchase")
	ResponseEntity<MessageDto> purchase(@RequestBody String customerUserName) {
		return ResponseEntity.ok(purchaseSvc.purchase(customerUserName));

	}
	
	@GetMapping("productPerformance")
	ResponseEntity<ArrayList<ProductPerformaceDto>> test() {
		return ResponseEntity.ok(purchaseSvc.checkHighPurchased());

	}
	
	

}
