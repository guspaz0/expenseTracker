package com.henry.expenseTracker.exceptions;

public class ForbidenUserException extends RuntimeException {
    public ForbidenUserException(String message) {
        super(message);
    }
}
