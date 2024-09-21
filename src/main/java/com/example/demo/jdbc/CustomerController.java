package com.example.demo.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;


	@GetMapping("/customer")
	public String customer(@RequestParam(value = "name", defaultValue = "World") String name) {

		customerService.dbCreateOperations();
		customerService.dbCheckOperations();
		customerService.dbCheckRestOperations();

		return String.format("Hello customer %s!", name);
	}

}