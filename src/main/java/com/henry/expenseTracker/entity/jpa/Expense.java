package com.henry.expenseTracker.entity.jpa;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Table(name="expenses")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Double amount;
    private String currency;

    @Temporal(TemporalType.DATE)
    @Column(name="emit_date")
    private LocalDate emitDate;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name="supplier_id")
    private Supplier supplier;

    @Column(name="user_id")
    private Long userId;

    @OneToMany
    @JoinTable(
            name="expirations",
            joinColumns = @JoinColumn(name="expense_id"),
            inverseJoinColumns = @JoinColumn(name="id")
    )
    private List<Expiration> expirations = new ArrayList<>();

    private int expires;

}
