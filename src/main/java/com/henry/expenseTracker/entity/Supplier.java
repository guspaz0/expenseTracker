package com.henry.expenseTracker.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="suppliers")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
