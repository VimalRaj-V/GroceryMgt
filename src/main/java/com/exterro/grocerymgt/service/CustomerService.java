package com.exterro.grocerymgt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.exterro.grocerymgt.dto.CustomerDto;
import com.exterro.grocerymgt.dto.CustomerPurchaseDto;
import com.exterro.grocerymgt.dto.MessageDto;
import com.exterro.grocerymgt.dto.PasswordDto;
import com.exterro.grocerymgt.dto.UserLoginDto;
import com.exterro.grocerymgt.exception.UserNotFoundException;
import com.exterro.grocerymgt.exception.WrongPasswordException;
import com.exterro.grocerymgt.model.Customer;

/*
Author name : vimalraj.vijayakumar
Creation Date : 21-Jun-2024
*/

@Service
public interface CustomerService {
	public Customer addCustomer(CustomerDto customerDto);
	public List<CustomerPurchaseDto> viewCustomers();
	public CustomerDto auhtenticateCustomer(UserLoginDto userLoginDto) throws UserNotFoundException, WrongPasswordException;
	public CustomerDto forgotPasswordMail(String customerMailId) throws UserNotFoundException ;
	public MessageDto authenticateOtp(PasswordDto passwordDto) throws WrongPasswordException;
	public MessageDto changePassword(CustomerDto customerDto);
	
}
