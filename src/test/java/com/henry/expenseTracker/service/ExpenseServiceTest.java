package com.henry.expenseTracker.service;

import com.henry.expenseTracker.dao.dto.expenseDto;
import com.henry.expenseTracker.entity.Expense;

public interface ExpenseServiceTest {
    void addExpense(expenseDto expenseDto);

    /**
     * Checks if expense expirations participation sum equals to 100% amount of the given expense
     * @param id identifier of expense com.henry.expenseTracker.entity class
     */
    void checkExpirations(int id);
    void removeExpense(int id);
    void updateExpense(Expense expense);

}
