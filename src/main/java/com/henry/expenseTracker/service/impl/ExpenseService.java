package com.henry.expenseTracker.service.impl;

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
public class ExpenseService implements IService<Expense> {
    private IDao<Expense> expenseDaoGeneric;
    private expenseIDao expenseIDao;

    public ExpenseService(){
        this.expenseIDao = new expenseDaoH2();
        this.expenseDaoGeneric = new expenseDaoH2();
    }

    @Override
    public List<Expense> findAll() {
        return expenseDaoGeneric.findAll();
    }

    @Override
    public Expense save(Expense expense) {
        return null;
    }

    @Override
    public Optional<Expense> findByPk(int id) {
        return expenseDaoGeneric.findByPk(id);
    }
    @Override
    public void delete(int id) {
        expenseDaoGeneric.delete(id);
    }

    @Override
    public Expense update(Expense expense) {
        return expenseDaoGeneric.update(expense);
    }
    
    public List<Expense> findAllRelations() {
        return expenseIDao.findAllRelations();
    }

    public List<ExpenseResponseDto> findAllRelationsByUser(int id) {
        return expenseIDao.findAllRelationsByUser(id);
    }
}
