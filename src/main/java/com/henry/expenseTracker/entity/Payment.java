package com.henry.expenseTracker.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date date;
    private double amount;

    @ManyToOne
    @JoinColumn(name="supplier_id")
    private Supplier supplier;

    @OneToMany(mappedBy = "payment"/*, fetch=FetchType.LAZY*/)
    private List<ExpirationPayments> expirations = new ArrayList<>();

//    @ManyToOne
//    @JoinColumn(name="user_id")
//    private User user;
    private Long user_id;

}
