package com.henry.expenseTracker.Dto.response;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
public class PaymentResponseDto {
    private Long id;
    private Date date;
    private Double amount;
    private Long userId;
    private Long supplierId;
    private List<ExpirationPaymentResponseDto> expirations;
}
