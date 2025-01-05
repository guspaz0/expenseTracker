package com.henry.expenseTracker.Dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CategoryResponseDto implements Serializable {
    private Long id;
    private String name;
    private String description;
}
