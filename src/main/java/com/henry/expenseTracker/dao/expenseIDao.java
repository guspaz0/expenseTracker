package com.henry.expenseTracker.dao;

import com.henry.expenseTracker.dao.dto.ExpenseRelationsDto;
import com.henry.expenseTracker.entity.Expense;
import com.henry.expenseTracker.entity.Expiration;

import java.util.List;

public interface expenseIDao {
    /**
     * List of expenses with all relationships included in database
     */
    List<Expense> findAllRelations();
    /**
     * List of expenses with all relationships included in database and belongs to the User
     * @param id Pk identifier of User in database
     */
    List<ExpenseRelationsDto> findAllRelationsByUser(int id);

    /**
     * Add expense category
     * @param id_cat Category primary key
     * @param id_exp Expense primary key
     */
    void addCategory(int id_cat,int id_exp);

    /**
     * remove expense category
     * @param id_cat Category primary key
     * @param id_exp Expense primary key
     */
    void removeCategory(int id_cat, int id_exp);

    /**
     * Add expense expiration
     * @param expiration expenseExpiration Object
     */
    void addExpiration(Expiration expiration);

    /**
     * remove expense expiration
     * @param expiration expenseExpiration primary key
     */
    void removeExpiration(Expiration expiration);

    /**
     * update expense expiration
     * @param id_expiration expenseExpiration primary key
     */
    void removeExpiration(int id_expiration);


}
