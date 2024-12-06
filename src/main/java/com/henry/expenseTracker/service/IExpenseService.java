package com.henry.expenseTracker.service;

import com.henry.expenseTracker.Dto.request.ExpenseRequestDto;
import com.henry.expenseTracker.Dto.response.ExpenseResponseDto;

import java.util.List;

public interface IExpenseService {

    List<ExpenseResponseDto> findAll();

    ExpenseResponseDto save(ExpenseRequestDto expense);

    ExpenseResponseDto findById(Long id) throws Exception;

    String delete(Long id) throws Exception;

    ExpenseResponseDto update(ExpenseRequestDto expense) throws Exception;
}
