package com.henry.expenseTracker.controller.api.Dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequestDto {
    private String email;
    private String password;

    public LoginRequestDto() {
    }


    public LoginRequestDto(String email, String password) {
        this.email=email;
        this.password=password;
    }

}

