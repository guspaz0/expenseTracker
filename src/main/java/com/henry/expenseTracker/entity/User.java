package com.henry.expenseTracker.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class User {
    private int id;
    private String name;
    private String email;
    private String password;

    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

}
