package dao;

import dao.dto.categoryDto;
import entity.Category;
import exceptions.categoryNotFoundException;
import exceptions.categoryRepeatedException;
import exceptions.expenseNotFoundException;

import java.util.List;

public interface categoryDao {
    /**
     * List all categories in database
     * @return List of Category entity class
     * */
    List<Category> findAll();
    /**
     * Find entity in database
     * @return Category entity class properties
     * @param id integer identifier of category entity
     * */
    Category findByPk(int id) throws categoryNotFoundException;
    /**
     * Create new Entity category in Database
     * @param categoryDto the category dto you want to update
     * */
    void create(categoryDto categoryDto) throws categoryRepeatedException;
    /**
     * Update category in database
     * @param category the category class you want to update
     * */
    void update(Category category) throws categoryRepeatedException;
    /**
     * Delete category in database
     * @param id identifier category in DB
     * */
    void delete(int id);
}
