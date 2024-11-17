package dao;

import dao.dto.categoryExpensesDto;
import entity.Category;
import entity.Expense;
import exceptions.categoryNotFoundException;
import exceptions.expenseNotFoundException;

import java.util.List;

public interface categoryExpensesDao {
    /**
     *
     * @param id expense identifier
     * @return List of categories to belongs the expense
     */
    List<Category> getCategories(int id) throws expenseNotFoundException;

    /**
     *
     * @param id category identifier
     * @return List of expenses belongs to category
     * @throws categoryNotFoundException
     */
    List<Expense> getExpenses(int id) throws categoryNotFoundException;
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
