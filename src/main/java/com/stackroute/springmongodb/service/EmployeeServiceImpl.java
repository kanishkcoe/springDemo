package com.stackroute.springmongodb.service;

import com.stackroute.springmongodb.domain.Employee;
import com.stackroute.springmongodb.exception.EmployeeAlreadyExistException;
import com.stackroute.springmongodb.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements IEmployeeService{
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) throws EmployeeAlreadyExistException {
        Optional<Employee> optional = employeeRepository.findById(employee.getEmpid());
        if(optional.isPresent()) {
            throw new EmployeeAlreadyExistException("Employee already exists");
        }
        Employee createEmployee = employeeRepository.save(employee);
        return createEmployee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> getAllEmployeeByNameStartingWith(String name) {
        return employeeRepository.findByempnameStartingWith(name);
    }

    @Override
    public List<Employee> getAllEmployeesFromCity(String city) {
        return employeeRepository.findAllEmployeeFromCity(city);
    }
}
