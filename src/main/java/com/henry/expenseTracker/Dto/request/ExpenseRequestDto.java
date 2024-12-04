package com.henry.expenseTracker.Dto.request;

import com.henry.expenseTracker.entity.Category;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpenseRequestDto {
    private Long id;
    private String description;

    @NotNull
    @NotBlank(message="Amount is mandatory")
    private Double amount;

    @NotBlank(message="Date is mandatory")
    private Date emitDate;

    @NotNull
    private Category category;

    @NotNull
    private int expires;

    @NotEmpty(message = "List of marks cannot be empty")
    private List<ExpirationRequestDto> expirations;

    @Min(value = 1, message = "Student should have enrolled in at least one subject")
    @Max(value = 4, message = "Student cannot be enrolled more than four subjects")

    @NotNull
    private int supplierId;

    @NotNull
    private int userId;

}
