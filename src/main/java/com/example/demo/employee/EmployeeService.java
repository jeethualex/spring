package com.example.demo.employee;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);
    EmployeeDto getEmployeeById(Long employeeId) throws EmployeeNotFoundException;
    List<EmployeeDto> getEmployees();
    void deleteEmployee(Long employeeId) throws EmployeeNotFoundException;
    EmployeeDto updateEmployee(EmployeeDto employeeDto) throws EmployeeNotFoundException;
}