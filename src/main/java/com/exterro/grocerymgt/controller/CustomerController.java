package com.exterro.grocerymgt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exterro.grocerymgt.dto.CustomerDto;
import com.exterro.grocerymgt.dto.CustomerPurchaseDto;
import com.exterro.grocerymgt.dto.MessageDto;
import com.exterro.grocerymgt.dto.PasswordDto;
import com.exterro.grocerymgt.dto.UserLoginDto;
import com.exterro.grocerymgt.exception.UserNotFoundException;
import com.exterro.grocerymgt.exception.WrongPasswordException;
import com.exterro.grocerymgt.model.Customer;
import com.exterro.grocerymgt.service.CustomerService;

/*
Author name : vimalraj.vijayakumar
Creation Date : 24-June-2024
*/

@RestController
@CrossOrigin("http://localhost:4200/")
public class CustomerController {
	@Autowired
	private CustomerService customerSvc;

	@PostMapping("register")
	public ResponseEntity<Customer> addCustomer(@RequestBody CustomerDto customerDto) {
		try {
			return ResponseEntity.ok(customerSvc.addCustomer(customerDto));
		} catch (Exception e) {
			return (new ResponseEntity("User Name already exists", HttpStatus.NOT_FOUND));
		}
	}

	@PostMapping("authenticate")
	ResponseEntity<?> authenticateCustomer(@RequestBody UserLoginDto userLoginDto) {
		try {
			CustomerDto customerDto = customerSvc.auhtenticateCustomer(userLoginDto);
			if (userLoginDto.getCustomerUserName().equals("admin") ) {
				return ResponseEntity.ok(new MessageDto("Admin"));
			}
			return ResponseEntity.ok(customerDto);
		} catch (UserNotFoundException e) {
			return (new ResponseEntity(new MessageDto("User Not Found"), HttpStatus.NOT_FOUND));
		} catch (WrongPasswordException e) {
			return (new ResponseEntity(new MessageDto("Wrong Password"), HttpStatus.FORBIDDEN));
		}
	}

	@GetMapping("authenticate/{customerMailId}")
	ResponseEntity<?> checkCustomer(@PathVariable String customerMailId) {
		try {
			CustomerDto customerDto = customerSvc.forgotPasswordMail(customerMailId);
			return ResponseEntity.ok(customerDto);
		} catch (UserNotFoundException e) {
			return (new ResponseEntity(new MessageDto("User Not Found"), HttpStatus.NOT_FOUND));
		}
	}

	@PostMapping("authenticateOtp")
	ResponseEntity<?> checkCustomerOtp(@RequestBody PasswordDto passwordDto) {
		try {
			MessageDto messageDto = customerSvc.authenticateOtp(passwordDto);
			return ResponseEntity.ok(messageDto);
		} catch (WrongPasswordException e) {
			return (new ResponseEntity(new MessageDto("Wrong OTP"), HttpStatus.FORBIDDEN));
		}

	}

	@PostMapping("changePassword")
	ResponseEntity<MessageDto> changeCustomerPassword(@RequestBody CustomerDto customerDto) {
		MessageDto messageDto = customerSvc.changePassword(customerDto);
		return ResponseEntity.ok(messageDto);
	}
	
	@GetMapping("viewCustomers")
	ResponseEntity<List<CustomerPurchaseDto>> viewCustomers() {
		return ResponseEntity.ok(customerSvc.viewCustomers());
	}

}
