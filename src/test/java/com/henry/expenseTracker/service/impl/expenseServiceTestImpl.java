package com.henry.expenseTracker.service.impl;

import com.henry.expenseTracker.dao.dto.expenseDto;
import com.henry.expenseTracker.entity.Expense;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.henry.expenseTracker.service.ExpenseServiceTest;
import com.henry.expenseTracker.service.expenseCategory;

@ExtendWith(MockitoExtension.class)
public class expenseServiceTestImpl implements ExpenseServiceTest {
    //testear que cada expensa tenga asignada una categoria
    @Mock
    private expenseCategory expenseCategory;


    @Override
    public void addExpense(expenseDto expenseDto) {

    }

    @Override
    public void checkExpirations(int id) {

    }

    @Override
    public void removeExpense(int id) {

    }

    @Override
    public void updateExpense(Expense expense) {

    }
}
