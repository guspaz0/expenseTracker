package com.henry.expenseTracker.dao.dto;

import com.henry.expenseTracker.entity.Category;
import com.henry.expenseTracker.entity.Expiration;
import com.henry.expenseTracker.entity.Supplier;
import com.henry.expenseTracker.entity.User;

import java.sql.Date;
import java.util.List;

public class ExpenseRelationsDto {
    private int id;
    private String description;
    private double amount;
    private Date date;
    private Category category;
    private List<Expiration> expirations;
    private Supplier supplier;
    private User user;

    public ExpenseRelationsDto(){}

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Expiration> getExpirations() {
        return expirations;
    }

    public void setExpirations(List<Expiration> expirations) {
        this.expirations = expirations;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "ExpenseRelationsDto{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                ", category=" + category.toString() +
                ", expirations=" + expirations +
                ", supplier=" + supplier.toString() +
                ", user=" + user +
                '}';
    }
}
