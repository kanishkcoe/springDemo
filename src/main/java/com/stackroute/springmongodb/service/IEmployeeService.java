package com.stackroute.springmongodb.service;

import com.stackroute.springmongodb.domain.Employee;
import com.stackroute.springmongodb.exception.EmployeeAlreadyExistException;

import java.util.List;

public interface IEmployeeService {

    public Employee saveEmployee(Employee employee) throws EmployeeAlreadyExistException;

    public List<Employee> getAllEmployees();

    public List<Employee> getAllEmployeeByNameStartingWith(String name);

    public List<Employee> getAllEmployeesFromCity(String city);
}
