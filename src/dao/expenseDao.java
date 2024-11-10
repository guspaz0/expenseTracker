package dao;

import dao.dto.expenseDto;
import entity.Expense;
import exceptions.expenseNotFoundException;
import exceptions.expenseRepeatedException;

import java.util.List;

public interface expenseDao {
    /**
     * List expenses in database
     * @return List of Expenses entity class
     * */
    List<Expense> findAll();
    /**
     * Find expense by the Primary key in database
     * @return Expense entity class properties
     * @param id integer identifier of expense entity
     * */
    Expense findByPk(int id) throws expenseNotFoundException;
    /**
     * Create new expense in database
     * @param expenseDto the expense dto you want to update
     * */
    void create(expenseDto expenseDto) throws expenseRepeatedException;
    /**
     * Update expense data in database
     * @param expense the category class you want to update
     * */
    void update(Expense expense) throws expenseRepeatedException;
    /**
     * Delete expense identified by primary key in database
     * @param id identifier category in DB
     * */
    void delete(int id);

}
