package com.henry.expenseTracker.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Expense {
    private int id;
    private String description;
    private double amount;
    private Date emit_date;
    private int category_id;
    private int expires;
    private int supplier_id;
    private int user_id;

    public Expense(double amount, Date emit_date, String description) {
        this.amount = amount;
        this.emit_date = emit_date;
        this.description = description;
    }

    public Expense(int id, double amount, Date emit_date, String description) {
        this.id = id;
        this.amount = amount;
        this.emit_date = emit_date;
        this.description = description;
    }
}
