package com.henry.expenseTracker.controller.api.impl;

import com.henry.expenseTracker.entity.Expense;
import com.henry.expenseTracker.service.impl.ExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) { this.expenseService = expenseService;}

    @GetMapping
    public ResponseEntity<List<Expense>> getAll(@RequestHeader Integer userId) {
        ResponseEntity<List<Expense>> response = null;
        if (userId.describeConstable().isPresent()) {
            response = ResponseEntity.status(HttpStatus.OK).body(expenseService.findAll());
        }
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expense> findById(@PathVariable Integer id) {
        Expense expense = expenseService.findByPk(id).orElse(null);
        return ResponseEntity.status(HttpStatus.OK).body(expense);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
        ResponseEntity<String> response = null;
        if (expenseService.findByPk(id).isPresent()) {
            expenseService.delete(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @PostMapping
    public ResponseEntity<Expense> create(@RequestBody Expense expense) {
        return ResponseEntity.ok(expenseService.save(expense));
    }

    @PutMapping
    public ResponseEntity<Expense> update(@RequestBody Expense expense) {
        return ResponseEntity.ok(expenseService.update(expense));
    }
}
