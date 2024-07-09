package com.exterro.grocerymgt.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exterro.grocerymgt.dto.GroceryDto;
import com.exterro.grocerymgt.dto.GroceryItemDto;
import com.exterro.grocerymgt.dto.MessageDto;
import com.exterro.grocerymgt.exception.ItemNotFoundException;
import com.exterro.grocerymgt.model.Cart;
import com.exterro.grocerymgt.model.CartItem;
import com.exterro.grocerymgt.model.Customer;
import com.exterro.grocerymgt.model.GroceryItem;
import com.exterro.grocerymgt.repository.CartRepo;
import com.exterro.grocerymgt.repository.CustomerRepo;
import com.exterro.grocerymgt.repository.GroceryRepo;


/*
Author name : vimalraj.vijayakumar
Creation Date : 24-June-2024
*/
@Service
public class GroceryServiceImpl implements GroceryService {
	private static Logger logger = LogManager.getLogger(GroceryServiceImpl.class);
	
	@Autowired
	private GroceryRepo groceryRepo;
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private CartRepo cartRepo;
	
	@Override
	public List<GroceryItemDto> viewGroceries(String customerUserName){
		logger.trace(customerUserName, " has viewed the groceries");
		Customer customer = customerRepo.findByCustomerUserName(customerUserName).get();
		Cart cart = cartRepo.findByCustomer(customer).get();

		Map<String,Integer> cartDetails = new HashMap<String,Integer>();
		for (CartItem cartItem: cart.getItemList()) {
			cartDetails.put(cartItem.getItemId(), cartItem.getQuantity());
		}
		List<GroceryItem> groceryItems= groceryRepo.findAll();
		List<GroceryItemDto> result = new ArrayList<GroceryItemDto>();
		for (GroceryItem groceryItem: groceryItems) {
			GroceryItemDto groceryItemDto;
			if(cartDetails.containsKey(groceryItem.getItemId())) {
				groceryItemDto = new GroceryItemDto(groceryItem, cartDetails.get(groceryItem.getItemId()));
			}
			else {
				groceryItemDto = new GroceryItemDto(groceryItem, 0);
			}
			result.add(groceryItemDto);
		}
		
		return result;
	}

	@Override
	public GroceryItem addItem(GroceryDto groceryDto) {
		GroceryItem groceryItem = new GroceryItem(groceryDto);
		GroceryItem groceryItem1 = groceryRepo.save(groceryItem);
		groceryItem1.setItemId("G"+Integer.toString(groceryItem1.getId()));
		logger.info("Grocery successfully added");
		return groceryRepo.save(groceryItem1);
	}
	
	@Override
	public GroceryItem modifyItem(String itemId, GroceryDto groceryDto) throws ItemNotFoundException {
		Optional<GroceryItem> opGroceryItem = groceryRepo.findByItemId(itemId);
		if (opGroceryItem.isPresent()) {
			GroceryItem groceryItem = new GroceryItem(opGroceryItem.get().getId(), groceryDto);
			logger.info(itemId, " is successfully modified");
			return groceryRepo.save(groceryItem);
		}
		logger.error("Invalid Item Id");
		throw new ItemNotFoundException("Invalid Item Id");
	}
	
	@Override
	public MessageDto deleteItem(String itemId) throws ItemNotFoundException {
		Optional<GroceryItem> opGroceryItem = groceryRepo.findByItemId(itemId);
		if (opGroceryItem.isPresent()) {
			groceryRepo.delete(opGroceryItem.get());

			logger.info("Item Successfully Deleted");
			return new MessageDto("Item Successfully Deleted");
		}
		logger.error("Invalid Item Id");
		throw new ItemNotFoundException("Invalid Item Id");
	}

}
