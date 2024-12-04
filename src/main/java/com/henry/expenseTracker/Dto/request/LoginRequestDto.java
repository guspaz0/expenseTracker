package com.henry.expenseTracker.Dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {

    @Email(message = "Please provide a valid email address")
    private String email;

    @NotNull(message= "Password is mandatory")
    private String password;
}

