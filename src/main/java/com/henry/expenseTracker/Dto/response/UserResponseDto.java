package com.henry.expenseTracker.Dto.response;

import lombok.*;

@Getter
@Setter
@Builder
public class UserResponseDto {
    private Long id;
    private String name;
    private String email;
}
