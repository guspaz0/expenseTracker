package com.henry.expenseTracker.Dto.request;

import com.henry.expenseTracker.entity.Category;
import com.henry.expenseTracker.entity.Supplier;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpenseRequestDto {
    private Long id;
    private String description;

    @NotNull
    @Min(value = 1, message = "amount must be greater than 1")
    private Double amount;

    @NotNull
    private LocalDate emitDate;

    @NotNull
    private Category category;

    @Min(value = 0, message = "amount is binary value(1 or 0)")
    @Max(value = 1, message = "amount is binary value(1 or 0)")
    private int expires = 0;

    private List<ExpirationRequestDto> expirations = new ArrayList<>();

    @NotNull
    private Supplier supplier;

    @NotNull
    private Long userId;

}
