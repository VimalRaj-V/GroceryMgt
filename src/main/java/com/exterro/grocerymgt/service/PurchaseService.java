package com.exterro.grocerymgt.service;

import java.util.ArrayList;
import java.util.List;

import com.exterro.grocerymgt.dto.CartItemDto;
import com.exterro.grocerymgt.dto.MessageDto;
import com.exterro.grocerymgt.dto.ProductPerformaceDto;

/*
Author name : vimalraj.vijayakumar
Creation Date : 26-June-2024
*/

public interface PurchaseService {
	public List<CartItemDto> viewCart(String customerUserName);
	public MessageDto purchase(String customerUserName);
	public ArrayList<ProductPerformaceDto> checkHighPurchased();
}
