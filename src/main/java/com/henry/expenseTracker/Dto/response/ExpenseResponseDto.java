package com.henry.expenseTracker.Dto.response;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpenseResponseDto {
    private Long id;
    private String description;
    private Double amount;
    private Date emitDate;
    private int expires;
    private CategoryResponseDto category;
    private SupplierResponseDto supplier;
    private UserResponseDto user;
    private List<ExpirationPaymentResponseDto> expirations;
}
