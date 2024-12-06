package com.henry.expenseTracker.Dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExpirationRequestDto {
    private Long id;

    private Long expenseId;

    @NotNull
    @NotBlank(message="expireDate is mandatory")
    private LocalDate expireDate;

    @NotBlank(message="participation is mandatory")
    @Max(value= 1, message="participation cant exceed 1.00 (100.00%)")
    private Double participation;
}
