package com.henry.expenseTracker.Dto.response;
import lombok.*;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpenseResponseDto implements Serializable {
    private Long id;
    private String description;
    private Double amount;
    private String currency;
    private LocalDate emitDate;
    private int expires;
    private CategoryResponseDto category;
    private SupplierResponseDto supplier;
    private Long userId;
    private List<ExpirationResponseDto> expirations = new ArrayList<>();
}
