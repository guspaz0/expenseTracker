package com.henry.expenseTracker.exceptions.controllers;

import com.henry.expenseTracker.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestController {

    private final String status = HttpStatus.BAD_REQUEST.name();
    private final Integer statusCode = HttpStatus.BAD_REQUEST.value();

    @ExceptionHandler(PaymentException.class)
    public BaseErrorResponse handlePaymentException(PaymentException exception){
        return ErrorResponse.builder()
                .message(exception.getMessage())
                .status(status)
                .statusCode(statusCode)
                .build();
    }

    @ExceptionHandler(ExpenseException.class)
    public BaseErrorResponse handleExpenseException(ExpenseException exception){
        return ErrorResponse.builder()
                .message(exception.getMessage())
                .status(status)
                .statusCode(statusCode)
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseErrorResponse handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<String>();
        ex.getAllErrors()
                .forEach(error->errors.add(error.getDefaultMessage()));
        return ErrorsResponse.builder()
                .errors(errors)
                .status(status)
                .statusCode(statusCode)
                .build();
    }
}
