package com.stackroute.springmongodb.exception;

public class EmployeeAlreadyExistException extends Exception{
    public EmployeeAlreadyExistException(String message) {
        super(message);
    }
}
