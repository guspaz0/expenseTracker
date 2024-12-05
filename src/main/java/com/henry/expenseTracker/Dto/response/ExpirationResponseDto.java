package com.henry.expenseTracker.Dto.response;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
public class ExpirationResponseDto {
    private Long id;
    private Long expenseId;
    private LocalDate expireDate;
    private Double participation;
}
