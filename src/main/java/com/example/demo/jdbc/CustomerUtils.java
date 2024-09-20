package com.example.demo.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerUtils {

	private static final Logger log = LoggerFactory.getLogger(CustomerUtils.class);

	@Autowired
	private CustomerRepository customerRepository;

	public void dbCreateOperations() {
		customerRepository.createData();
	}

	public void dbCheckOperations() {
		customerRepository.checkOperations();
	}

}