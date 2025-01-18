package com.henry.expenseTracker.service.abstract_service;

import com.henry.expenseTracker.Dto.request.ExpenseRequestDto;
import com.henry.expenseTracker.Dto.response.ExpenseResponseDto;
import com.henry.expenseTracker.entity.jpa.Expense;
import com.henry.expenseTracker.util.constants.SortType;
import org.hibernate.query.Page;

import java.util.List;

public interface IExpenseService {

    List<ExpenseResponseDto> findAll(Integer page, Integer size, SortType sortType);

    ExpenseResponseDto save(ExpenseRequestDto expense);

    ExpenseResponseDto findById(Long id) throws Exception;

    String delete(Long id) throws Exception;

    ExpenseResponseDto update(ExpenseRequestDto expense) throws Exception;

    String FIELD_BY_SORT = "description";
}
