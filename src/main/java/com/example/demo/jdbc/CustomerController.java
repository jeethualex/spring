package com.example.demo.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CustomerController {

	@Autowired
	private CustomerUtils customerUtils;


	@GetMapping("/customer")
	public String customer(@RequestParam(value = "name", defaultValue = "World") String name) {

		customerUtils.dbCreateOperations();
		customerUtils.dbCheckOperations();
		customerUtils.dbCheckRestOperations();

		return String.format("Hello customer %s!", name);
	}

}