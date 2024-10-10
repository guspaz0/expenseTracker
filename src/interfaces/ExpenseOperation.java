package interfaces;

import entity.Expense;

@FunctionalInterface
public interface ExpenseOperation {
    void operate(Expense expense);
}
