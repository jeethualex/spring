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

		log.info("Inserting -> {}", customerRepository.insert(new Customer("Ramesh", "Fadatare")));
		log.info("Inserting -> {}", customerRepository.insert(new Customer("Suresh", "Fadatare")));

		log.info("Employee id 10011 -> {}", customerRepository.findById(5));
		log.info("Employee id 10012L -> {}", customerRepository.findById(6));

		log.info("Update 10011L -> {}", customerRepository.update(new Customer(5, "Ram", "Stark")));
		log.info("Update 10012L -> {}", customerRepository.update(new Customer(6, "Shyam", "Stark")));

		log.info("Employee id 10011 -> {}", customerRepository.findById(5));
		log.info("Employee id 10012L -> {}", customerRepository.findById(6));

		log.info("Delete Employee id 10011 -> {}", customerRepository.findById(5));
		customerRepository.deleteById(5);

		log.info("All customers -> {}", customerRepository.findAll());

	}

}