package com.henry.expenseTracker.service;

import com.henry.expenseTracker.entity.Supplier;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IService<T> {
    /**
     * @return List of Objects com.henry.expenseTracker.entity stored in database
     */
    List<T> findAll();

    /**
     * @param t Object com.henry.expenseTracker.entity you want to add in database
     * @return Object created
     */
    T save(T t);

    /**
     * @param id identifier of Object com.henry.expenseTracker.entity you want to search
     * @return Object com.henry.expenseTracker.entity
     */
    Optional<T> findById(Long id);

    /**
     * List of Objects com.henry.expenseTracker.entity stored in database
     * @param id identifier of Object com.henry.expenseTracker.entity you want to delete
     */
    void delete(Long id);

    /**
     * @param t Object to be updated
     * @return Object updated
     */
    void update(T t);
}
