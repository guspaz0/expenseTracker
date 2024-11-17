package dao;

import entity.Expense;
import entity.Expiration;

import java.util.List;

public interface expenseIDao {
    /**
     * List of expenses with all relationships included in database
     */
    List<Expense> findAllRelations() throws Exception;
    /**
     * List of expenses with all relationships included in database and belongs to the User
     * @param id Pk identifier of User in database
     */
    List<Expense> findAllRelationsByUser(int id) throws Exception;

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
