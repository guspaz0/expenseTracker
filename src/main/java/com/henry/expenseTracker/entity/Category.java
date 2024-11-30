package com.henry.expenseTracker.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Category {
    private Integer id;
    private String name;
    private String description;

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
