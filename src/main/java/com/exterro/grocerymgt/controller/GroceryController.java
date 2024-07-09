package com.exterro.grocerymgt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exterro.grocerymgt.dto.GroceryDto;
import com.exterro.grocerymgt.dto.GroceryItemDto;
import com.exterro.grocerymgt.dto.MessageDto;
import com.exterro.grocerymgt.exception.ItemNotFoundException;
import com.exterro.grocerymgt.model.GroceryItem;
import com.exterro.grocerymgt.service.GroceryService;

/*
Author name : vimalraj.vijayakumar
Creation Date : 25-June-2024
*/
@RestController
@CrossOrigin("http://localhost:4200/")
public class GroceryController {
	@Autowired
	private GroceryService grocerySvc;

	@GetMapping("view/{customerUserName}")
	public ResponseEntity<List<GroceryItemDto>> viewGroceries(@PathVariable String customerUserName) {
		return ResponseEntity.ok(grocerySvc.viewGroceries(customerUserName));
	}

	@PostMapping("addGrocery")
	ResponseEntity<GroceryItem> addItem(@RequestBody GroceryDto groceryDto) {
		return ResponseEntity.ok(grocerySvc.addItem(groceryDto));

	}
	
	@PostMapping("modifyGrocery/{itemId}")
	ResponseEntity<GroceryItem> modifyItem(@PathVariable String itemId, @RequestBody GroceryDto groceryDto) {
		try {
			return ResponseEntity.ok(grocerySvc.modifyItem(itemId, groceryDto));
		} catch (ItemNotFoundException e) {
			return(new ResponseEntity(new MessageDto("Item Not Found"), HttpStatus.NOT_FOUND));
		}

	}
	
	@DeleteMapping("deleteGrocery/{itemId}")
	ResponseEntity<MessageDto> deleteItem(@PathVariable String itemId) {
		try {
			return ResponseEntity.ok(grocerySvc.deleteItem(itemId));
		} catch (ItemNotFoundException e) {
			return(new ResponseEntity(new MessageDto("Item Not Found"), HttpStatus.NOT_FOUND));
		}

	}
}
