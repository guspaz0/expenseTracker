package com.henry.expenseTracker.controller.views.Dto;

import java.sql.Date;

public class ExpirationRequestDto {
    private Date expiration;
    private Double participation;
    private int expense;

    public ExpirationRequestDto() {
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public Double getParticipation() {
        return participation;
    }

    public void setParticipation(Double participation) {
        this.participation = participation;
    }

    public int getExpense() {
        return expense;
    }

    public void setExpense(int expense) {
        this.expense = expense;
    }
}
