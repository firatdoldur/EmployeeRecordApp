package com.example.employeerecordapp.advice;


import com.example.employeerecordapp.advice.exception.EmployeeNotFoundException;
import com.example.employeerecordapp.advice.exception.ParameterException;
import com.example.employeerecordapp.advice.exception.WinnerNotFoundException;
import com.example.employeerecordapp.model.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFound(EmployeeNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                e.getMessage(),
                HttpStatus.NOT_FOUND,
                new Date().getTime());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ParameterException.class)
    public ResponseEntity<Object> handleParameterException(ParameterException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                new Date().getTime());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WinnerNotFoundException.class)
    public ResponseEntity<Object> handleParameterException(WinnerNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                e.getMessage(),
                HttpStatus.NOT_FOUND,
                new Date().getTime());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
