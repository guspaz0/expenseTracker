package com.henry.expenseTracker.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse extends BaseErrorResponse {
    private String message;
}
