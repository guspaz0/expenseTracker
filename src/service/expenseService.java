package service;

import dao.IDao;
import dao.expenseIDao;
import entity.Category;
import entity.Expense;
import entity.User;
import exceptions.expenseNotFoundException;


import java.util.List;
import java.util.Optional;

public class expenseService {
    private IDao<Expense> expenseDaoGeneric;
    private expenseIDao expenseDao;
    private User user;
    private Expense expense;

    public expenseService(IDao<Expense> expenseDaoGeneric){
        this.expenseDaoGeneric = expenseDaoGeneric;
    }

    public expenseService(expenseIDao expenseDao, User user){
        this.expenseDao = expenseDao;
        this.user = user;
    }
    
    public List<Expense> findAll() {
        return expenseDaoGeneric.findAll();
    }
    
    public Optional<Expense> findOne(int id) {
        return expenseDaoGeneric.findByPk(id);
    }
    
    public void delete(int id) {
        expenseDaoGeneric.delete(id);
    }
    
    public void update(Expense expense) throws expenseNotFoundException {
        expenseDaoGeneric.update(expense);
    }
    public void addCategory(int id) {
        expenseDao.addCategory(id, expense.getId());
    }
    
    public List<Category> getCategories(int id) {
        return List.of();
    }
    
    public List<Expense> findAllRelations() throws Exception {
        if (this.user != null) return expenseDao.findAllRelationsByUser(this.user.getId());
        return expenseDao.findAllRelations();
    }
}
