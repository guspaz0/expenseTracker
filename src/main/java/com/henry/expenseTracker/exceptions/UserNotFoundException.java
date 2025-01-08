package com.henry.expenseTracker.exceptions;

public class UserNotFoundException extends RuntimeException {

    private static final String ERROR_MESSAGE = "User no exist in %s";
    public UserNotFoundException(String message) {
        super(String.format(ERROR_MESSAGE, message));
    }
}
