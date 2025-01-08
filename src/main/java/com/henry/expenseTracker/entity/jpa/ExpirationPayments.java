package com.henry.expenseTracker.entity.jpa;

import jakarta.persistence.*;
import lombok.*;



@Entity
@Table(name="expiration_payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpirationPayments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="expiration_part")
    private Double expirationPart;

    @Column(name="payment_part")
    private Double paymentPart;

    @Column(name="payment_id")
    private Long paymentId;

    @Column(name="expiration_id")
    private Long expirationId;
}
