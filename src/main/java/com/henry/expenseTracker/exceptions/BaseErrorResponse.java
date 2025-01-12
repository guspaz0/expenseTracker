package com.henry.expenseTracker.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseErrorResponse implements Serializable {

    private String status;
    private int statusCode;

//    public BaseErrorResponse(String message)
//    {
//        super();
//        this.message = message;
//    }
}
