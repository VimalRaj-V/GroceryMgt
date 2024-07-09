package com.exterro.grocerymgt.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.exterro.grocerymgt.dto.CustomerDto;
import com.exterro.grocerymgt.dto.CustomerPurchaseDto;
import com.exterro.grocerymgt.dto.MessageDto;
import com.exterro.grocerymgt.dto.PasswordDto;
import com.exterro.grocerymgt.dto.UserLoginDto;
import com.exterro.grocerymgt.exception.UserNotFoundException;
import com.exterro.grocerymgt.exception.WrongPasswordException;
import com.exterro.grocerymgt.model.Cart;
import com.exterro.grocerymgt.model.CartItem;
import com.exterro.grocerymgt.model.Customer;
import com.exterro.grocerymgt.model.Purchase;
import com.exterro.grocerymgt.repository.CartRepo;
import com.exterro.grocerymgt.repository.CustomerRepo;
import com.exterro.grocerymgt.repository.PurchaseRepo;

/*
Author name : vimalraj.vijayakumar
Creation Date : 21-Jun-2024
*/
@Service
public class CustomerServiceImpl implements CustomerService {
	private static Logger logger = LogManager.getLogger(CustomerServiceImpl.class);
	
	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private CartRepo cartRepo;

	@Autowired
	private PurchaseRepo purchaseRepo;

	@Autowired
	private EmailSenderService emailSenderService;

	@Override
	public Customer addCustomer(CustomerDto customerDto) {
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		String encryptedPassword = bcrypt.encode(customerDto.getCustomerPassword());
		customerDto.setCustomerPassword(encryptedPassword);
		Customer customer = new Customer(customerDto);
		Customer cus1 = customerRepo.save(customer);
		logger.info(cus1.getCustomerUserName() + " successfully added in the customer list");
		cus1.setCustomerId("C" + Integer.toString(cus1.getId()));
		logger.trace(customerDto+" is successfully added");
		Cart cart = new Cart(cus1);
		cartRepo.save(cart);
		logger.info("Cart has been successfully created for "+ cus1.getCustomerUserName());
		return cus1;
	}

	

	@Override
	public CustomerDto auhtenticateCustomer(UserLoginDto userLoginDto)
			throws UserNotFoundException, WrongPasswordException {
		logger.trace("Authenticate Customer function is called");
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		Optional<Customer> opCustomer = customerRepo.findByCustomerUserName(userLoginDto.getCustomerUserName());
		if (opCustomer.isPresent()) {
			Customer customer = opCustomer.get();
			CustomerDto customerDto = new CustomerDto();
			customerDto.setCustomerName(customer.getCustomerName());
			customerDto.setCustomerUserName(customer.getCustomerUserName());
			customerDto.setCustomerMailId(customer.getCustomerMailId());
			if (bcrypt.matches(userLoginDto.getCustomerPassword(), customer.getCustomerPassword())) {
				logger.info("Password successfully authenticated for "+ userLoginDto.getCustomerUserName());
				return customerDto;
			} else {
				logger.error("You have entered the Wrong Password!!");
				throw new WrongPasswordException("You have entered the Wrong Password!!");
			}

		}
		logger.error("No user is found for this user name!!");
		throw new UserNotFoundException("No user is found for this user name!!");
	}


	@Override
	public CustomerDto forgotPasswordMail(String customerMailId) throws UserNotFoundException {
		logger.trace("Forgot Password Mail function is called");
		Optional<Customer> opCustomer = customerRepo.findByCustomerMailId(customerMailId);
		if (opCustomer.isPresent()) {
			Customer customer = opCustomer.get();
			CustomerDto customerDto = new CustomerDto();
			customerDto.setCustomerName(customer.getCustomerName());
			customerDto.setCustomerUserName(customer.getCustomerUserName());
			customerDto.setCustomerMailId(customerMailId);
			int otp = (int) (Math.random() * 9000) + 1000;
			String customerOtp = Integer.toString(otp);
			customer.setCustomerOtp(customerOtp);
			customerRepo.save(customer);
			logger.info("Password successfully modified");
			emailSenderService.sendEmail(customer.getCustomerMailId(), "Online Grocery Shopping - Reg",
					buildMessage(customer.getCustomerName(), customerOtp));
			return customerDto;
		}
		logger.error("Mail Id doesn't exists");
		throw new UserNotFoundException("Mail Id doesn't exists");
	}

	private String buildMessage(String customerName, String customerOtp) {
		return "Dear " + customerName + ",\r\n" + "  Your OTP is " + customerOtp;
	}

	@Override
	public MessageDto authenticateOtp(PasswordDto passwordDto) throws WrongPasswordException {
		logger.trace("Authenticate OTP function is called");
		Customer customer = customerRepo.findByCustomerMailId(passwordDto.getCustomerMailId()).get();
		if (customer.getCustomerOtp().equals(passwordDto.getCustomerOtp())) {
			customer.setCustomerOtp(null);
			customerRepo.save(customer);
			logger.info("OTP successfully verified");
			return new MessageDto("Valid OTP");
		}
		logger.error("OTP Mismatches");
		throw new WrongPasswordException("Invalid OTP");
	}

	@Override
	public MessageDto changePassword(CustomerDto customerDto) {
		
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		String encryptedPassword = bcrypt.encode(customerDto.getCustomerPassword());
		customerDto.setCustomerPassword(encryptedPassword);

		Customer customer = customerRepo.findByCustomerMailId(customerDto.getCustomerMailId()).get();
		customer.setCustomerPassword(encryptedPassword);
		
		customerRepo.save(customer);
		logger.info("Password successfully changed");
		return new MessageDto("Password successfully changed");
	}

	@Override
	public List<CustomerPurchaseDto> viewCustomers() {
		logger.trace("View Customers is called");
		List<Purchase> purchaseList = purchaseRepo.findAll();
		Map<String,Integer> purchaseCount = new HashMap<String,Integer>();
		Map<String,Integer> amountCount = new HashMap<String,Integer>();
		
		for(Purchase p :purchaseList) {
			List<CartItem>  cartItems = p.getItemList();
			String customerId = p.getCustomerId();
			int totalAmount = 0;
			for (CartItem cartItem: cartItems) {
				totalAmount += cartItem.getTotalPrice();
			}
			if(purchaseCount.containsKey(customerId)) {
				purchaseCount.put(customerId, purchaseCount.get(customerId) + 1);
				amountCount.put(customerId, amountCount.get(customerId) + totalAmount);
			}
			else {
				purchaseCount.put(customerId, 1);
				amountCount.put(customerId, totalAmount);
			}
		}
		
		
		List<Customer> customerList = customerRepo.findAll();
		List<CustomerPurchaseDto> customerPurchaseDtoList = new ArrayList<CustomerPurchaseDto>();
		
		for (Customer customer: customerList) {
			String customerId = customer.getCustomerId();
			CustomerPurchaseDto customerPurchaseDto;
			if (purchaseCount.containsKey(customerId)) {
				customerPurchaseDto = new CustomerPurchaseDto(customer, purchaseCount.get(customerId), amountCount.get(customerId));
			}
			else {
				customerPurchaseDto = new CustomerPurchaseDto(customer, 0, 0);
			}
				
			customerPurchaseDtoList.add(customerPurchaseDto);
		}
		
		Collections.sort(customerPurchaseDtoList, new Comparator<CustomerPurchaseDto>() {
	        public int compare(CustomerPurchaseDto o1, CustomerPurchaseDto o2) {
	            return (int) (o2.getTotalPurchasedAmount() - o1.getTotalPurchasedAmount());
	        }
	    });
		
		return customerPurchaseDtoList;
	}

}
