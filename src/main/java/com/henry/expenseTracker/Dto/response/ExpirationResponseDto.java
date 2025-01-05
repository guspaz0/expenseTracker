package com.henry.expenseTracker.Dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ExpirationResponseDto implements Serializable {
    private Long id;
    private Long expenseId;
    private LocalDate expireDate;
    private Double participation;
    private Double amount;
}
