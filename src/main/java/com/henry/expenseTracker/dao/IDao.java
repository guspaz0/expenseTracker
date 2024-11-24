package com.henry.expenseTracker.dao;

import java.util.List;
import java.util.Optional;

public interface IDao<T> {

    /**
     * @param t Object com.henry.expenseTracker.entity you want to add in database
     * @return Object created
     */
    T save(T t);

    /**
     * @param id identifier of Object com.henry.expenseTracker.entity you want to search
     * @return Object com.henry.expenseTracker.entity
     */
    Optional<T> findByPk(int id);
    /**
     * List of Objects com.henry.expenseTracker.entity stored in database
     * @param id identifier of Object com.henry.expenseTracker.entity you want to delete
     */
    void delete(int id);

    /**
     *
     * @return List of Objects com.henry.expenseTracker.entity stored in database
     */
    List<T> findAll();
    T update(T t);
}
