package com.henry.expenseTracker.Dto.response;

import com.henry.expenseTracker.entity.UserRole;
import lombok.*;

@Getter
@Setter
@Builder
public class UserResponseDto {
    private Long id;
    private String name;
    private String email;
    private String country;
    private String currency;
    private UserRole userRole;
}
