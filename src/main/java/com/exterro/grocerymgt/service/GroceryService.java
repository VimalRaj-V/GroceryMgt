package com.exterro.grocerymgt.service;

import java.util.List;

import com.exterro.grocerymgt.dto.GroceryDto;
import com.exterro.grocerymgt.dto.GroceryItemDto;
import com.exterro.grocerymgt.dto.MessageDto;
import com.exterro.grocerymgt.exception.ItemNotFoundException;
import com.exterro.grocerymgt.model.GroceryItem;

/*
Author name : vimalraj.vijayakumar
Creation Date : 24-Jun-2024
*/

public interface GroceryService {
	public List<GroceryItemDto> viewGroceries(String customerUserName);
	public GroceryItem addItem(GroceryDto groceryDto);
	public GroceryItem modifyItem(String itemId, GroceryDto groceryDto) throws ItemNotFoundException ;
	public MessageDto deleteItem(String itemId) throws ItemNotFoundException;
}
