package com.henry.expenseTracker.entity;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Expiration {
    private int id;
    private int expenseId;
    private Date expiration;
    private double participation;

}
