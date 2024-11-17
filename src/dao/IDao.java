package dao;

import entity.Expense;
import exceptions.expenseRepeatedException;

import java.util.List;
import java.util.Optional;

public interface IDao<T> {

    /**
     * @param t Object entity you want to add in database
     * @return Object created
     */
    T save(T t);

    /**
     * @param id identifier of Object entity you want to search
     * @return Object entity
     */
    Optional<T> findByPk(int id);
    /**
     * List of Objects entity stored in database
     * @param id identifier of Object entity you want to delete
     */
    void delete(int id);

    /**
     *
     * @return List of Objects entity stored in database
     */
    List<T> findAll();
    T update(T t);
}
