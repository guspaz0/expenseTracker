package com.henry.expenseTracker.Dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class SupplierResponseDto {
    private Long id;
    private String name;
}
