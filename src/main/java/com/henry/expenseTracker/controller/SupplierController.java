package com.henry.expenseTracker.controller;

import com.henry.expenseTracker.Dto.request.SupplierRequestDto;
import com.henry.expenseTracker.Dto.response.SupplierResponseDto;
import com.henry.expenseTracker.entity.Supplier;
import com.henry.expenseTracker.service.impl.SupplierService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/supplier")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping
    public ResponseEntity<List<SupplierResponseDto>> findAll() {
        return ResponseEntity.ok(supplierService.findAll());
    }

    @SneakyThrows
    @GetMapping("/{id}")
    public ResponseEntity<SupplierResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(supplierService.findById(id));
    }

    @SneakyThrows
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
            return ResponseEntity.ok(supplierService.delete(id));
    }
    @SneakyThrows
    @PutMapping
    public ResponseEntity<SupplierResponseDto> update(@RequestBody SupplierRequestDto supplier) {
        return ResponseEntity.ok(supplierService.update(supplier));
    }

    @PostMapping
    public ResponseEntity<SupplierResponseDto> save(@RequestBody SupplierRequestDto supplier) {
        log.info("body: "+supplier.toString());
        return ResponseEntity.ok(supplierService.save(supplier));
    }
}
