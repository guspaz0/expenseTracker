package com.henry.expenseTracker.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;;

@Entity
@Table(name="expiration_payments")
@Getter
@Setter
@NoArgsConstructor
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
