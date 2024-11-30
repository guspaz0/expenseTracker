package com.henry.expenseTracker.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Supplier {
    private int id;
    private String name;

    public Supplier(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
