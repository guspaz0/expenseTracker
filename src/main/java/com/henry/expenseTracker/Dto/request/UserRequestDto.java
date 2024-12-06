package com.henry.expenseTracker.Dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Data
public class UserRequestDto {
    private Long id;

    @NotNull(message = "Name is mandatory")
    private String name;

    @NotNull
    @Email(message="email must be a valid email")
    private String email;

    @NotNull
    @Size
    private String password;
}
