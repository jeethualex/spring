package com.example.demo.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerUtils {

	private static final Logger log = LoggerFactory.getLogger(CustomerUtils.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	private CustomerRepository customerRepository;

	public void dbCreateOperations() {
		log.info("Creating tables");
		jdbcTemplate.execute("DROP TABLE IF EXISTS customers");
		jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS customers(" +
				"id INT NOT NULL AUTO_INCREMENT, first_name VARCHAR(255), last_name VARCHAR(255), PRIMARY KEY (id))");

		// Split up the array of whole names into an array of first/last names
		List<Object[]> splitUpNames = Arrays.asList("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long").stream()
				.map(name -> name.split(" "))
				.collect(Collectors.toList());

		// Use a Java 8 stream to print out each tuple of the list
		splitUpNames.forEach(name -> log.info(String.format("Inserting customer record for %s %s", name[0], name[1])));

		// Uses JdbcTemplate's batchUpdate operation to bulk load data
		jdbcTemplate.batchUpdate("INSERT INTO customers(first_name, last_name) VALUES (?,?)", splitUpNames);
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