package com.example.demo.jdbc;

import com.example.demo.employee.EmployeeDto;
import com.example.demo.employee.EmployeeNotFoundException;

import java.util.List;

public interface CustomerService {
    void dbCreateOperations();
    void dbCheckRestOperations();
    void dbCheckOperations();

    List<Customer> getCustomers();

    Customer getCustomerById(long customerId) throws CustomerNotFoundException;

    int saveCustomer(Customer customer) throws CustomerNotFoundException;

    int updateCustomer(Customer customer) throws CustomerNotFoundException;

    void deleteCustomer(Long customerId) throws CustomerNotFoundException;
}