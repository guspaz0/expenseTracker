package com.henry.expenseTracker.Dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRequestDto {
    private Long id;

    @NotNull
    private String name;

    @NotNull
    @Email(message="email must be a valid email")
    private String email;

    @NotNull
    @Size
    private String password;
}
