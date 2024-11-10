package dao;

import dao.dto.userExpensesDto;
import exceptions.expenseNotFoundException;
import exceptions.userNotFoundException;

public interface userExpensesDao {
    /**
     * Add relationship between user-expense entity to the database
     * @param userExpensesDto userExpensesDto class
     * */
    void add(userExpensesDto userExpensesDto) throws expenseNotFoundException, userNotFoundException;
    /**
     * Delete relationship between user-expense entity to the database
     * @param userExpensesDto userExpensesDto class
     * */
    void delete(userExpensesDto userExpensesDto) throws expenseNotFoundException, userNotFoundException;
}
