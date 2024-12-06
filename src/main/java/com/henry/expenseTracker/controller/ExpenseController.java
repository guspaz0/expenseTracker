package com.henry.expenseTracker.controller;

import com.henry.expenseTracker.Dto.request.ExpenseRequestDto;
import com.henry.expenseTracker.Dto.response.ExpenseResponseDto;
import com.henry.expenseTracker.entity.Expense;
import com.henry.expenseTracker.service.impl.ExpenseService;
import jakarta.validation.Valid;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/expense")
public class ExpenseController {
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping
    public ResponseEntity<List<ExpenseResponseDto>> findAll() {
        return ResponseEntity.ok(expenseService.findAll());
    }

    @SneakyThrows
    @GetMapping("/{id}")
    public ResponseEntity<ExpenseResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(expenseService.findById(id));
    }

    @SneakyThrows
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return ResponseEntity.ok(expenseService.delete(id));
    }

    @SneakyThrows
    @PutMapping
    public ResponseEntity<ExpenseResponseDto> update(@Valid @RequestBody ExpenseRequestDto expense) {
        return new ResponseEntity<>(expenseService.update(expense), HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<ExpenseResponseDto> save(@Valid @RequestBody ExpenseRequestDto expense) {
        //log.info("Body: "+ expense.toString());
        return new ResponseEntity<>(expenseService.save(expense), HttpStatus.CREATED);
    }
}
