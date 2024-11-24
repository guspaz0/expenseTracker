package com.henry.expenseTracker.service.impl;

import com.henry.expenseTracker.dao.IDao;
import com.henry.expenseTracker.dao.impl.categoryDaoH2;
import com.henry.expenseTracker.entity.Category;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements IDao<Category> {
    private IDao<Category> categoryDao;

    public CategoryService(){
        this.categoryDao = new categoryDaoH2();
    }

    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    @Override
    public Optional<Category> findByPk(int id) {
        return categoryDao.findByPk(id);
    }

    @Override
    public Category save(Category category) {
        return categoryDao.save(category);
    }

    @Override
    public Category update(Category category) {
        return categoryDao.update(category);
    }

    @Override
    public void delete(int id) {
        categoryDao.delete(id);
    }
}
