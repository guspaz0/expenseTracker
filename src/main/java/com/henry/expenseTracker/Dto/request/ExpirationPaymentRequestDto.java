package com.henry.expenseTracker.Dto.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpirationPaymentRequestDto {

    private Long id;

    @Digits(integer = 1, fraction = 2)
    private Double paymentPart;

    @Digits(integer = 1, fraction = 2)
    private Double expirationPart;

    @NotNull(message = "expirationId cannot bu null")
    private Long expirationId;

    private Long paymentId;
}
