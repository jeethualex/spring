package com.example.demo;

import com.example.demo.jdbc.customer.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private CustomerService customerService;

	@Test
	void contextLoads() {

		customerService.dbCreateOperations();
		customerService.dbCheckOperations();
		customerService.dbCheckRestOperations();
	}

}
