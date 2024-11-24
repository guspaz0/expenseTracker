package com.henry.expenseTracker.service.impl;

import com.henry.expenseTracker.dao.IDao;
import com.henry.expenseTracker.dao.impl.supplierDaoH2;
import com.henry.expenseTracker.entity.Supplier;
import com.henry.expenseTracker.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService implements IService<Supplier> {
    private final IDao<Supplier> supplierDao;

    public SupplierService(){
        this.supplierDao = new supplierDaoH2();
    }

    @Override
    public List<Supplier> findAll() {
        return supplierDao.findAll();
    }

    @Override
    public Supplier save(Supplier supplier) {
        return supplierDao.save(supplier);
    }

    @Override
    public Optional<Supplier> findByPk(int id) {
        return supplierDao.findByPk(id);
    }

    @Override
    public Supplier update(Supplier supplier) {
        return supplierDao.update(supplier);
    }

    @Override
    public void delete(int id) {
        supplierDao.delete(id);
    }
}
