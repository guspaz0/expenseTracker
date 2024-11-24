package com.henry.expenseTracker.service.impl;

import com.henry.expenseTracker.dao.IDao;
import com.henry.expenseTracker.dao.impl.userDaoH2;
import com.henry.expenseTracker.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IDao<User> {
    private IDao<User> userDao;

    public UserService(){
        this.userDao = new userDaoH2();
    }

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
