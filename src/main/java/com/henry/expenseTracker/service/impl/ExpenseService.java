package com.henry.expenseTracker.service.impl;

import com.henry.expenseTracker.controller.views.Dto.ExpenseRequestDto;
import com.henry.expenseTracker.controller.views.Dto.ExpenseRequestUpdateDto;
import com.henry.expenseTracker.dao.IDao;
import com.henry.expenseTracker.dao.dto.ExpenseResponseDto;
import com.henry.expenseTracker.dao.expenseIDao;
import com.henry.expenseTracker.dao.impl.expenseDaoH2;
import com.henry.expenseTracker.entity.Expense;
import com.henry.expenseTracker.service.IService;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {
    private expenseDaoH2 expenseDaoGeneric;
    private expenseIDao expenseIDao;

    public ExpenseService(){
        this.expenseIDao = new expenseDaoH2();
        this.expenseDaoGeneric = new expenseDaoH2();
    }

    public List<Expense> findAll() {
        return expenseDaoGeneric.findAll();
    }


    public Expense save(Expense expense) {
        ExpenseResponseDto request = new ExpenseResponseDto();
        return expenseDaoGeneric.save(expense);
    }

    public Optional<Expense> findByPk(int id) {
        return expenseDaoGeneric.findByPk(id);
    }

    public void delete(int id) {
        expenseDaoGeneric.delete(id);
    }

    public Expense update(Expense expense) {

        return expenseDaoGeneric.update(expense);
    }
    
    public Optional<ExpenseResponseDto> findAllRelationsByPk(int id) {
        return expenseIDao.findAllRelationsByPk(id);
    }

    public List<ExpenseResponseDto> findAllRelationsByUser(int id) {
        return expenseIDao.findAllRelationsByUser(id);
    }
}
