package com.henry.expenseTracker.controller.api.impl;

import com.henry.expenseTracker.controller.api.IController;
import com.henry.expenseTracker.entity.Supplier;
import com.henry.expenseTracker.service.impl.SupplierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController implements IController<Supplier> {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping
    public ResponseEntity<List<Supplier>> findAll() {
        return ResponseEntity.ok(supplierService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Supplier> findByPk(Integer id) {
        Supplier supplier = supplierService.findByPk(id).orElse(null);
        return ResponseEntity.status(HttpStatus.OK).body(supplier);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(Integer id) {
        ResponseEntity<String> response = null;
        if (supplierService.findByPk(id).isPresent()) {
            supplierService.delete(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }
    @PutMapping
    public ResponseEntity<Supplier> update(Supplier supplier) {
        return ResponseEntity.ok(supplierService.update(supplier));
    }

    @PostMapping
    public ResponseEntity<Supplier> save(Supplier supplier) {
        return ResponseEntity.ok(supplierService.save(supplier));
    }
}
