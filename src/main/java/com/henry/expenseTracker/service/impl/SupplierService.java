package com.henry.expenseTracker.service.impl;


import com.henry.expenseTracker.entity.Supplier;
import com.henry.expenseTracker.repository.SupplierRepository;
import com.henry.expenseTracker.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService implements IService<Supplier> {
    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository){
        this.supplierRepository = supplierRepository;
    }

    @Override
    public List<Supplier> findAll() {
        return supplierRepository.findAll();
    }

    @Override
    public Supplier save(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @Override
    public Optional<Supplier> findById(Long id) {
        return supplierRepository.findById(id);
    }

    @Override
    public void update(Supplier supplier) {
        Optional<Supplier> optionalSupplier = this.findById(supplier.getId());
        if (optionalSupplier.isPresent()) {
            supplierRepository.save(supplier);
        }
    }

    @Override
    public void delete(Long id) {
        supplierRepository.deleteById(id);
    }
}
