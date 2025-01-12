package com.henry.expenseTracker.service.abstract_service;

import com.henry.expenseTracker.Dto.request.ExpenseRequestDto;
import com.henry.expenseTracker.Dto.request.ExpirationRequestDto;
import com.henry.expenseTracker.Dto.response.ExpenseResponseDto;
import com.henry.expenseTracker.Dto.response.ExpirationResponseDto;

import java.util.List;

public interface IExpirationsService {
    List<ExpirationResponseDto> findAllByExpenseId(Long Id);

    ExpirationResponseDto save(ExpirationRequestDto expense);

    ExpirationResponseDto findById(Long id) throws Exception;

    String delete(Long id) throws Exception;

    ExpirationResponseDto update(ExpirationRequestDto expense) throws Exception;
}
