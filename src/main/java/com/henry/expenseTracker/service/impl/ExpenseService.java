package com.henry.expenseTracker.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.henry.expenseTracker.Dto.request.ExpenseRequestDto;
import com.henry.expenseTracker.Dto.response.ExpenseResponseDto;
import com.henry.expenseTracker.entity.Expense;
import com.henry.expenseTracker.repository.ExpenseRepository;
import com.henry.expenseTracker.service.IExpenseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService implements IExpenseService {
    private final ExpenseRepository expenseRepository;
    private final ObjectMapper objectMapper;

    public ExpenseService(ExpenseRepository expenseRepository, ObjectMapper objectMapper) {
        this.expenseRepository = expenseRepository;
        this.objectMapper = objectMapper;
    }
    @Override
    public List<ExpenseResponseDto> findAll() {
        return expenseRepository.findAll()
                .stream().map(this::mapToDTO)
                .toList();
    }

    @Override
    public ExpenseResponseDto save(ExpenseRequestDto expense) {
        return mapToDTO(expenseRepository.save(mapToEntity(expense)));
    }
    @Override
    public ExpenseResponseDto findById(Long id) throws Exception {
        return mapToDTO(expenseRepository.findById(id)
                .orElseThrow(()-> new Exception("Expense id: "+id+" not found")));
    }

    @Override
    public String delete(Long id) throws Exception {
        this.findById(id);
        expenseRepository.deleteById(id);
        return "Expense id: "+id+" deleted successfully";
    }

    @Override
    public ExpenseResponseDto update(ExpenseRequestDto expense) throws Exception {
        this.findById(expense.getId());
        return mapToDTO(expenseRepository.save(mapToEntity(expense)));
    }

    private ExpenseResponseDto mapToDTO(Expense expense) {
        return objectMapper.convertValue(expense, ExpenseResponseDto.class);
    }

    private Expense mapToEntity(ExpenseRequestDto expenseRequestDto) {
        return objectMapper.convertValue(expenseRequestDto, Expense.class);
    }
}
