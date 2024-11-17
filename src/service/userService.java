package service;

import dao.IDao;
import entity.User;
import exceptions.userNotFoundException;

import java.util.List;
import java.util.Optional;

public class userService implements IDao<User> {
    private IDao<User> userDao;

    public userService(IDao<User> userDao){
        this.userDao = userDao;
    }

    public userService(){}

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User save(User user) {
        return userDao.save(user);
    }

    @Override
    public Optional<User> findByPk(int id) {
        return userDao.findByPk(id);
    }

    @Override
    public void delete(int id) {
        userDao.delete(id);
    }

    @Override
    public User update(User user) {
        return userDao.update(user);
    }
}
