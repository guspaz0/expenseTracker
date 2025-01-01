package com.henry.expenseTracker.exceptions.controllers;

import com.henry.expenseTracker.exceptions.BaseErrorResponse;
import com.henry.expenseTracker.exceptions.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundController {

    @ExceptionHandler(value = Exception.class)

    public @ResponseBody BaseErrorResponse handleException(Exception ex) {
        return ErrorResponse.builder()
                .message(ex.getMessage())
                .status(HttpStatus.NOT_FOUND.name())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .build();
    }

}
