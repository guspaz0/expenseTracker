package com.henry.expenseTracker.entity;

import java.sql.Date;
import java.util.List;

public class Expense {
    private int id;
    private String description;
    private double amount;
    private Date emit_date;
    private int category_id;
    private int expires;
    private int supplier_id;
    private int user_id;

    public Expense() {}

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getEmit_date() {
        return emit_date;
    }

    public void setEmit_date(Date emit_date) {
        this.emit_date = emit_date;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getExpires() {
        return expires;
    }

    public void setExpires(int expires) {
        this.expires = expires;
    }

    public int getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(int supplier_id) {
        this.supplier_id = supplier_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
