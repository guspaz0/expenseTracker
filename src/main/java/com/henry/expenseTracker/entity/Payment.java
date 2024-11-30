package com.henry.expenseTracker.entity;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    private int id;
    private int id_supplier;
    private int id_user;
    private double amount;
    private Date date;

}
