package com.henry.expenseTracker.controller.api.impl;

import com.henry.expenseTracker.controller.api.IController;
import com.henry.expenseTracker.entity.Supplier;
import com.henry.expenseTracker.service.impl.SupplierService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
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
    public ResponseEntity<Supplier> findById(@PathVariable Long id) {
        Supplier supplier = supplierService.findById(id).orElse(null);
        return ResponseEntity.status(HttpStatus.OK).body(supplier);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        ResponseEntity<String> response = null;
        if (supplierService.findById(id).isPresent()) {
            supplierService.delete(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }
    @PutMapping
    public ResponseEntity<String> update(@RequestBody Supplier supplier) {
        try {
            supplierService.update(supplier);
            return ResponseEntity.ok("Supplier updated Successfully");
        } catch (Exception e){
            return ResponseEntity.ok("Error updating Supplier");
        }

    }

    @PostMapping
    public ResponseEntity<Supplier> save(@RequestBody Supplier supplier) {
        log.info("body: "+supplier.toString());
        return ResponseEntity.ok(supplierService.save(supplier));
    }
}
