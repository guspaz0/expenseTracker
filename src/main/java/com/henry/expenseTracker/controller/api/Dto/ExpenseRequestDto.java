package com.henry.expenseTracker.controller.api.Dto;

import java.sql.Date;
import java.util.List;

public class ExpenseRequestDto {
    private String description;
    private double amount;
    private Date date;
    private int category_id;
    private int expires;
    private List<ExpirationRequestDto> expirations;
    private int supplier_id;
    private int user_id;

    public ExpenseRequestDto() {
    }

    public ExpenseRequestDto(String description, double amount, Date date, int category, int supplier_id, int user_id) {
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.category_id = category;
        this.supplier_id = supplier_id;
        this.user_id = user_id;
    }

    public int getExpires() {
        return this.expires;
    }

    public void setExpires(int expires) {
        this.expires = expires;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCategory_id() {
        return this.category_id;
    }

    public void setCategory_id(int category) {
        this.category_id = category;
    }

    public List<ExpirationRequestDto> getExpirations() {
        return this.expirations;
    }

    public void setExpirations(List<ExpirationRequestDto> expirations) {
        this.expirations = expirations;
    }

    public int getSupplier_id() {
        return this.supplier_id;
    }

    public void setSupplier_id(int supplier_id) {
        this.supplier_id = supplier_id;
    }

    public int getUser_id() {
        return this.user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

}
