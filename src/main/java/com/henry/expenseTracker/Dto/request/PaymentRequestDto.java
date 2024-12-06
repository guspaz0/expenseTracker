package com.henry.expenseTracker.Dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PaymentRequestDto {

    private Long id;

    private LocalDate date = LocalDate.now();

    @Min(value = 0, message = "amount must be more than 0")
    private Double amount;

    @NotNull(message = "supplierId cannot be null")
    private Long supplierId;

    @NotNull(message = "userId cannot be null")
    private Long userId;

    private List<ExpirationPaymentRequestDto> expirations;

}
