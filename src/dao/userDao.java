package dao;

import dao.dto.userDto;
import entity.User;
import exceptions.userEmailRepeatedException;
import exceptions.userNotFoundException;

import java.util.List;

public interface userDao {
    /**
     * List of all users in database
     * @return List of User entity class
     * */
    List<User> findAll();
    /**
     * Find user by the Primary Key in database
     * @param id integer
     * @return User entity class with properties
     * */
    User findByPk(int id) throws userNotFoundException;

    /**
     * Create a new user into the database
     * @param userDto userDto class
     * */
    void create(userDto userDto) throws userEmailRepeatedException;
    /**
     * Update User Data in Database
     * @param User class entity with properties to be updated.
     * */
    void update(User User) throws userNotFoundException;
    /**
     * Delete a User from Db
     * @param id integer that identifies the User in the database
     * */
    void delete(int id) throws userNotFoundException;

}
