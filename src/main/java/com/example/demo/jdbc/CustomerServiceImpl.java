package com.example.demo.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	public void dbCreateOperations() {
		customerRepository.testData();
	}

	public void dbCheckRestOperations() {
		customerRepository.testRestOperations();
	}

	public void dbCheckOperations() {
		customerRepository.testDbOperations();
	}

	@Override
	public List<Customer> getCustomers() {
		List<Customer> customers = customerRepository.findAll();
		return customers;
	}

	@Override
	public Customer getCustomerById(long customerId) throws CustomerNotFoundException {
		Optional<Customer> customer = customerRepository.findById(customerId);
		if (customer.isEmpty()) {
			throw new CustomerNotFoundException("Customer with id - " + customerId + " not found.");
		}
		return customer.get();
	}

	@Override
	public int saveCustomer(Customer customer) throws CustomerNotFoundException {

		int createdCustomer = customerRepository.insert(customer);
		return createdCustomer;
	}

	@Override
	public int updateCustomer(Customer customer) throws CustomerNotFoundException {

		Optional<Customer> retrievedCustomer = customerRepository.findById(customer.getId());
		if (retrievedCustomer.isEmpty()) {
			throw new CustomerNotFoundException("Customer with id - " + customer.getId() + " not found.");
		}
		Customer customer1 = retrievedCustomer.get();
		customer1.setFirstName(customer.getFirstName());
		customer1.setLastName(customer.getLastName());

		int createdCustomer = customerRepository.update(customer1);
		return createdCustomer;
	}

	@Override
	public void deleteCustomer(Long customerId) throws CustomerNotFoundException {
		Optional<Customer> customer = customerRepository.findById(customerId);
		if (customer.isEmpty()) {
			throw new CustomerNotFoundException("Customer with id - " + customerId + " not found.");
		}
		customerRepository.deleteById(customerId);
	}

}