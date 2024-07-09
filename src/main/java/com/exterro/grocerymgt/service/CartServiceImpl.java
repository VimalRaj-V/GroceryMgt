package com.exterro.grocerymgt.service;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exterro.grocerymgt.dto.CartDto;
import com.exterro.grocerymgt.dto.MessageDto;
import com.exterro.grocerymgt.model.Cart;
import com.exterro.grocerymgt.model.Customer;
import com.exterro.grocerymgt.repository.CartRepo;
import com.exterro.grocerymgt.repository.CustomerRepo;

/*
Author name : vimalraj.vijayakumar
Creation Date : 26-Jun-2024
*/
@Service
public class CartServiceImpl implements CartService {
	private static Logger logger = LogManager.getLogger(CartServiceImpl.class);
	@Autowired
	private CartRepo cartRepo;
	
	@Autowired
	private CustomerRepo customerRepo;

	@Override
	public MessageDto addToCart(CartDto cartDto) {
		Customer customer = customerRepo.findByCustomerUserName(cartDto.getCustomerUserName()).get();
		logger.trace(customer.getCustomerName()+"'s cart is added");
		Optional<Cart> opCustomerCart = cartRepo.findByCustomer(customer);
		if (opCustomerCart.isPresent()) {
			Cart customerCart = opCustomerCart.get();
			customerCart.setItemList(cartDto.getItemList());
			cartRepo.save(customerCart);
			logger.info("Cart Successfully added");
			return  new MessageDto("Cart Successfully added");
		}
		logger.error("Cart Failed to add");
		return new MessageDto("Failed");
	}
}
