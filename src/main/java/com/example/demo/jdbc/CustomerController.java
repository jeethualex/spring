package com.example.demo.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CustomerController {

	private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private CustomerUtils customerUtils;


	@GetMapping("/customer")
	public String customer(@RequestParam(value = "name", defaultValue = "World") String name) {

		customerUtils.dbCheckRestOperations();

		return String.format("Hello customer %s!", name);
	}

}