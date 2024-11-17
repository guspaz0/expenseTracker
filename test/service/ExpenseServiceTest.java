package service;

import dao.dto.expenseDto;
import entity.Expense;

public interface ExpenseServiceTest {
    void addExpense(expenseDto expenseDto);

    /**
     * Checks if expense expirations participation sum equals to 100% amount of the given expense
     * @param id identifier of expense entity class
     */
    void checkExpirations(int id);
    void removeExpense(int id);
    void updateExpense(Expense expense);

}
