package com.example.demo.jdbc;

import com.example.demo.employee.Employee;
import com.example.demo.employee.EmployeeDto;
import com.example.demo.employee.EmployeeMapper;
import com.example.demo.employee.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

	@GetMapping(value = "/customers")
	public ResponseEntity<List<Customer>> getCustomers() {
		List<Customer> customers = customerService.getCustomers();
		return new ResponseEntity<>(customers, HttpStatus.OK);
	}

	@GetMapping(value = "/customer/{customerId}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable("customerId") Long customerId)
			throws CustomerNotFoundException {
		try {
			Customer customer = customerService.getCustomerById(customerId);
			return new ResponseEntity<>(customer, HttpStatus.OK);
		} catch (CustomerNotFoundException customerNotFoundException) {
			throw customerNotFoundException;
		}
	}

	@PostMapping(value = "/customer")
	public ResponseEntity<Integer> createCustomer(@RequestBody Customer customer) throws CustomerNotFoundException {
		int createdCustomer = customerService.saveCustomer(customer);
		return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
	}

	@PutMapping(value = "/customer")
	public ResponseEntity<Integer> updateCustomer(@RequestBody Customer customer)
			throws CustomerNotFoundException {
		int createdCustomer = customerService.updateCustomer(customer);
		return new ResponseEntity<>(createdCustomer, HttpStatus.OK);
	}

	@DeleteMapping(value = "/customer/{customerId}")
	public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable("customerId") Long customerId)
			throws CustomerNotFoundException {
		customerService.deleteCustomer(customerId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}


}