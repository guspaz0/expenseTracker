package service.impl;

import dao.IDao;
import entity.Supplier;

import java.util.List;
import java.util.Optional;

public class supplierService {
    private final IDao<Supplier> supplierDao;

    public supplierService(IDao<Supplier> supplierDao){
        this.supplierDao = supplierDao;
    }

    public List<Supplier> findAll() {
        return supplierDao.findAll();
    }

    public Supplier registerSupplier(Supplier supplier) {
        return supplierDao.save(supplier);
    }

    public Optional<Supplier> findByPk(int id) {
        return supplierDao.findByPk(id);
    }

    public Supplier update(Supplier supplier) {
        return supplierDao.update(supplier);
    }

    public void delete(int id) {
        supplierDao.delete(id);
    }
}
