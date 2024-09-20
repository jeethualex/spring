package com.example.demo.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerUtils {

	@Autowired
	private CustomerRepository customerRepository;

	public void dbCreateOperations() {
		customerRepository.createData();
	}

	public void dbCheckRestOperations() {
		customerRepository.checkRestOperations();
	}

	public void dbCheckOperations() {
		customerRepository.checkOperations();
	}

}