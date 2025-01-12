package com.henry.expenseTracker.entity.jpa;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "category")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

}
