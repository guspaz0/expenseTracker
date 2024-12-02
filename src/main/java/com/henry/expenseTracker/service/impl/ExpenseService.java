package com.henry.expenseTracker.service.impl;

import com.henry.expenseTracker.entity.Expense;
import com.henry.expenseTracker.repository.ExpenseRepository;
import com.henry.expenseTracker.service.IService;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService implements IService<Expense>{

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }
    @Override
    public List<Expense> findAll() {
        return expenseRepository.findAll();
    }

    @Override
    public Expense save(Expense expense) {
        //ExpenseResponseDto request = new ExpenseResponseDto();
        return expenseRepository.save(expense);
    }
    @Override
    public Optional<Expense> findById(Long id) {
        return expenseRepository.findById(id);
    }
    @Override
    public void delete(Long id) {
        expenseRepository.deleteById(id);
    }

    @Override
    public void update(Expense expense) {
        Optional<Expense> optionalExpense = this.findById(expense.getId());
        if (optionalExpense.isPresent()) {
            expenseRepository.save(expense);
        }
    }
    
//    public Optional<ExpenseResponseDto> findAllRelationsByPk(int id) {
//        return expenseIDao.findAllRelationsByPk(id);
//    }
//
//    public List<ExpenseResponseDto> findAllRelationsByUser(int id) {
//        return expenseIDao.findAllRelationsByUser(id);
//    }
}
