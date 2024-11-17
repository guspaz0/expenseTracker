package service;

import dao.IDao;
import entity.Category;


import java.util.List;
import java.util.Optional;

public class categoryService implements IDao<Category> {
    private IDao<Category> categoryDao;

    public categoryService(IDao<Category> categoryDao){
        this.categoryDao = categoryDao;
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
