package dao;

import dao.dto.categoryExpensesDto;
import exceptions.categoryNotFoundException;
import exceptions.expenseNotFoundException;

public interface categoryExpensesDao {
    /**
     * Add relationship between category-expenses to Database
     * @param categoryExpensesDto identifier category in DB
     * */
    void add(categoryExpensesDto categoryExpensesDto) throws expenseNotFoundException, categoryNotFoundException;
    /**
     * Delete relationship between category-expenses to Database
     * @param categoryExpensesDto identifier category in DB
     * */
    void delete(categoryExpensesDto categoryExpensesDto) throws expenseNotFoundException, categoryNotFoundException;
}
