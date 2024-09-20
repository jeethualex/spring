package com.example.demo.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CustomerController {

	private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	JdbcTemplate jdbcTemplate;


	@GetMapping("/customer")
	public String customer(@RequestParam(value = "name", defaultValue = "World") String name) {

		log.info("Querying for customer records where first_name = 'Josh':");
		jdbcTemplate.query(
						"SELECT id, first_name, last_name FROM customers WHERE first_name = ?",
						(rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name")), "Josh")
				.forEach(customer -> log.info(customer.toString()));

		return String.format("Hello customer %s!", name);
	}

}