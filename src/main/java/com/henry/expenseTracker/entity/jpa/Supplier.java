package com.henry.expenseTracker.entity.jpa;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="suppliers")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
