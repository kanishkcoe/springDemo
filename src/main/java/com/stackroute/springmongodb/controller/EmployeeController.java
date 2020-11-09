package com.stackroute.springmongodb.controller;

import com.stackroute.springmongodb.domain.Employee;
import com.stackroute.springmongodb.exception.EmployeeAlreadyExistException;
import com.stackroute.springmongodb.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/employeeservice")
public class EmployeeController {

    private IEmployeeService employeeService;

    private ResponseEntity responseEntity;

    @Autowired
    public EmployeeController(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/employee")
    public ResponseEntity<?> saveEmployee(@RequestBody Employee employee) {
        try {
            Employee createdEmployee = employeeService.saveEmployee(employee);
            responseEntity = new ResponseEntity(createdEmployee, HttpStatus.CREATED);
        } catch (EmployeeAlreadyExistException e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/employees")
    public ResponseEntity getAllEmployee() {
        try {
            List<Employee> employeeList = employeeService.getAllEmployees();
            responseEntity = new ResponseEntity(employeeList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity("Some Internal error try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/employees/name/{startswith}")
    public ResponseEntity<?> getAllEmployeeStartingWith(@PathVariable("startswith") String startswith) {
        return new ResponseEntity(employeeService.getAllEmployeeByNameStartingWith(startswith), HttpStatus.OK);
    }

    @GetMapping("/employees/city/{cityname}")
    public ResponseEntity<?> getAllEmployeeFromCity(@PathVariable("cityname") String cityname) {
        return new ResponseEntity(employeeService.getAllEmployeesFromCity(cityname), HttpStatus.OK);
    }
}
