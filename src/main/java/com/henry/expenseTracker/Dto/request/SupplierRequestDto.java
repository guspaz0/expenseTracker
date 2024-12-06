package com.henry.expenseTracker.Dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SupplierRequestDto {
    private Long id;

    @NotNull
    @NotBlank(message="name is mandatory")
    private String name;
}
