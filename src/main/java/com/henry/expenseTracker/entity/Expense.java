package com.henry.expenseTracker.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="expenses")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private double amount;

    @Temporal(TemporalType.DATE)
    private Date emit_date;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name="supplier_id")
    private Supplier supplier;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="user_id")
    private Long user_id;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="expirations",
            joinColumns = @JoinColumn(name="expense_id"),
            inverseJoinColumns = @JoinColumn(name="id")
    )
    private List<Expiration> expirations = new ArrayList<>();

    private int expires;


    public Expense(double amount, Date emit_date, String description) {
        this.amount = amount;
        this.emit_date = emit_date;
        this.description = description;
    }

    public Expense(Long id, double amount, Date emit_date, String description) {
        this.id = id;
        this.amount = amount;
        this.emit_date = emit_date;
        this.description = description;
    }
}
