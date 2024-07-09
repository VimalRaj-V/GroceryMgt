package com.exterro.grocerymgt.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.exterro.grocerymgt.dto.CartDto;
import com.exterro.grocerymgt.model.Cart;
import com.exterro.grocerymgt.model.CartItem;
import com.exterro.grocerymgt.model.Customer;
import com.exterro.grocerymgt.repository.CartRepo;

/*
Author name : vimalraj.vijayakumar
Creation Date : 03-July-2024
*/

@ExtendWith(MockitoExtension.class)
class CartServiceImplTest {
	@Mock
    private CartRepo cartRepo;
    
    @InjectMocks
    private CartService cartSvc;
	
	@Test
	void test() {
		String itemId = "G2";
		int quantity = 2;
		int totalPrice = 120;
		CartItem cartItem = new CartItem(itemId, quantity, totalPrice);
		
		String customerId = "C2";
		List<CartItem> itemList = new ArrayList<>();
		itemList.add(cartItem);
		
		CartDto cartDto = new CartDto(customerId, itemList);
		Cart cart = new Cart(1,new Customer(),itemList);
		when(cartRepo.save(cart)).thenReturn(cart);
	}

}
