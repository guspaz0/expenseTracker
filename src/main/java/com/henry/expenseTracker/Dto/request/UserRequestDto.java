package com.henry.expenseTracker.Dto.request;

import com.henry.expenseTracker.util.constants.UserRole;
import jakarta.validation.constraints.Email;
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
    private String country;

    @NotNull
    private String currency;


    private UserRole userRole = UserRole.ROLE_USER;

    @NotNull
    @Size
    private String password;
}
