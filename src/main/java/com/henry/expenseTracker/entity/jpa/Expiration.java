package com.henry.expenseTracker.entity.jpa;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="expirations")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Expiration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="expense_id")
    private Long expenseId;

    @Temporal(TemporalType.DATE)
    @Column(name="expire_date")
    private LocalDate expireDate;

    @OneToMany(
            mappedBy = "expirationId",
            fetch = FetchType.LAZY
    )
    private List<ExpirationPayments> payments = new ArrayList<>();

    private double participation;
}
