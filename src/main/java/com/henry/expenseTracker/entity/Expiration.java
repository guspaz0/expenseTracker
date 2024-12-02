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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="expense_id")
//    private Expense expense;
    private Long expense_id;

    @Temporal(TemporalType.DATE)
    private Date expiration;

    @OneToMany(mappedBy = "expiration", fetch = FetchType.LAZY)
    private List<ExpirationPayments> payments = new ArrayList<>();

    private double participation;
}
