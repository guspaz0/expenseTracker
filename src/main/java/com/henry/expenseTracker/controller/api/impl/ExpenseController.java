package com.henry.expenseTracker.controller.api.impl;

import com.henry.expenseTracker.controller.api.IController;
import com.henry.expenseTracker.entity.Expense;
import com.henry.expenseTracker.service.impl.ExpenseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/expense")
public class ExpenseController implements IController<Expense> {
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping
    public ResponseEntity<List<Expense>> findAll() {
        return ResponseEntity.ok(expenseService.findAll());
    }

    @Override
    public ResponseEntity<List<Expense>> findAll(Long id) {
        return IController.super.findAll(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expense> findById(@PathVariable Long id) {
        Expense expense = expenseService.findById(id).orElse(null);
        return ResponseEntity.ok(expense);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            expenseService.delete(id);
            return ResponseEntity.ok("Expense successfully deleted");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Error deleting Expense");
        }
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody Expense expense) {
        Optional<Expense> optionalExpense = expenseService.findById(expense.getId());
        if (optionalExpense.isPresent()) {
            expenseService.save(expense);
            return ResponseEntity.ok("Expense successfully updated");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Error Updating Expense");
        }
    }

    @PostMapping
    public ResponseEntity<Expense> save(@RequestBody Expense expense) {
        log.info("Body: "+ expense.toString());
        return ResponseEntity.ok(expenseService.save(expense));
    }
}
