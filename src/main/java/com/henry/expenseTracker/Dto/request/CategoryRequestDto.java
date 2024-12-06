package com.henry.expenseTracker.Dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryRequestDto {

    private Long id;

    @NotBlank(message="name is mandatory")
    private String name;

    @NotBlank(message="description is mandatory")
    private String description;
}
