package com.henry.expenseTracker.entity;

import java.sql.Date;

public class Payment {
    private int id;
    private int id_supplier;
    private int id_user;
    private double amount;
    private Date date;

    public Payment(){}

    public Payment(int id, int id_supplier, int id_user, double amount, Date date) {
        this.id = id;
        this.id_supplier = id_supplier;
        this.id_user = id_user;
        this.amount = amount;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_supplier() {
        return id_supplier;
    }

    public void setId_supplier(int id_supplier) {
        this.id_supplier = id_supplier;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
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
}
