package com.exterro.grocerymgt.service;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.exterro.grocerymgt.dto.CustomerDto;
import com.exterro.grocerymgt.model.Customer;
import com.exterro.grocerymgt.repository.CustomerRepo;

/*
Author name : vimalraj.vijayakumar
Creation Date : 09-Jul-2024
*/

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {
	@Mock
    private CustomerRepo customerRepo;
    
    @InjectMocks
    private CustomerService customerSvc;
    
    private Customer testCustomer;
	@Test
	void test() {
		fail("Not yet implemented");
	}

}
