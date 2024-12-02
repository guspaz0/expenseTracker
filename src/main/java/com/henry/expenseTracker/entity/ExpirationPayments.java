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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double expiration_part;
    private Double payment_part;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="payment_id")
    private Payment payment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="expiration_id")
    private Expiration expiration;
}
