package com.henry.expenseTracker.dao;

import com.henry.expenseTracker.dao.dto.ExpenseResponseDto;
import com.henry.expenseTracker.entity.Expense;
import com.henry.expenseTracker.entity.Expiration;

import java.util.List;
import java.util.Optional;

public interface expenseIDao {
    /**
     * List of expenses with all relationships included in database
     */
    Optional<ExpenseResponseDto> findAllRelationsByPk(int id);
    /**
     * List of expenses with all relationships included in database and belongs to the User
     * @param id Pk identifier of User in database
     */
    List<ExpenseResponseDto> findAllRelationsByUser(int id);


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
