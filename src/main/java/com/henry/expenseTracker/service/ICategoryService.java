package com.henry.expenseTracker.service;

import com.henry.expenseTracker.Dto.request.CategoryRequestDto;
import com.henry.expenseTracker.Dto.response.CategoryResponseDto;
import lombok.SneakyThrows;

import java.util.List;

public interface ICategoryService {
    List<CategoryResponseDto> findAll();

    CategoryResponseDto findById(Long id) throws Exception;

    CategoryResponseDto save(CategoryRequestDto category);

    CategoryResponseDto update(CategoryRequestDto category) throws Exception;

    String delete(Long id) throws Exception;
}
