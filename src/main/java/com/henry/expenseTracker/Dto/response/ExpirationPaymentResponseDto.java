package com.henry.expenseTracker.Dto.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpirationPaymentResponseDto {
    private Long id;
    private Double paymentPart;
    private Double expirationPart;
    private Long expirationId;
    private Long paymentId;
}
