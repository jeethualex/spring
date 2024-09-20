package com.example.demo;

import com.example.demo.jdbc.CustomerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	private CustomerUtils customerUtils;

	@Override
	public void run(String... strings) {
		customerUtils.dbCreateOperations();
		customerUtils.dbCheckOperations();
	}
}