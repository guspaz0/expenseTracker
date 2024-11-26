package com.henry.expenseTracker.controller.views.Dto;

import com.henry.expenseTracker.entity.*;

import java.sql.Date;
import java.util.List;

public class ExpenseRequestUpdateDto extends Expense {
    private int id;
    private String description;
    private double amount;
    private Date date;
    private int category_id;
    private List<Expiration> expirations;

    public ExpenseRequestUpdateDto(){}

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory(int category_id) {
        this.category_id = category_id;
    }

    public List<Expiration> getExpirations() {
        return expirations;
    }

    public void setExpirations(List<Expiration> expirations) {
        this.expirations = expirations;
    }


    @Override
    public String toString() {
        return "ExpenseRelationsDto{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                ", category=" + category_id +
                ", expirations=" + expirations +
                '}';
    }
}
