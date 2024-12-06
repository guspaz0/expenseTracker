package com.henry.expenseTracker.Dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
public class PaymentResponseDto {
    private Long id;
    private LocalDate date;
    private Double amount;
    private Long userId;
    private Long supplierId;
    private List<ExpirationPaymentResponseDto> expirations;
}
