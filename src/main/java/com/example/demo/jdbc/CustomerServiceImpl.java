package com.example.demo.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	public void dbCreateOperations() {
		customerRepository.testData();
	}

	public void dbCheckRestOperations() {
		customerRepository.testRestOperations();
	}

	public void dbCheckOperations() {
		customerRepository.testDbOperations();
	}

}