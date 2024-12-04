package com.henry.expenseTracker.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="expirations")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Expiration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long expense_id;

    @Temporal(TemporalType.DATE)
    private Date expiration;

    @OneToMany(
            mappedBy = "expirationId",
            fetch = FetchType.LAZY
    )
    private List<ExpirationPayments> payments = new ArrayList<>();

    private double participation;
}
