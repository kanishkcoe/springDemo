package com.stackroute.springmongodb.repository;

import com.stackroute.springmongodb.domain.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


public interface EmployeeRepository extends MongoRepository<Employee, String> {

    //all the crud operations can be done with this
    //findById()
    //findByXXX (XXX - can be any other property of our class)

    //findByEmpname()
    //findByEmail()

    List<Employee> findByempnameStartingWith(String name);

    //Now in this scenario city is not the property of my document so we have to write a query for the same
    @Query("{'address.city' : {$in : [?0]}}")
    List<Employee> findAllEmployeeFromCity(String city);
}
