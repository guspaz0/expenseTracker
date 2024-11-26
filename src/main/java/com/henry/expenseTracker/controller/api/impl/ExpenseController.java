package com.henry.expenseTracker.controller.api.impl;

import com.henry.expenseTracker.controller.views.Dto.ExpenseRequestDto;
import com.henry.expenseTracker.controller.views.Dto.ExpenseRequestUpdateDto;
import com.henry.expenseTracker.dao.dto.ExpenseResponseDto;
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
    public ResponseEntity<List<ExpenseResponseDto>> getAll(@RequestHeader Integer userId) {
        ResponseEntity<List<ExpenseResponseDto>> response = null;
        if (userId.describeConstable().isPresent()) {
            response = ResponseEntity.status(HttpStatus.OK).body(expenseService.findAllRelationsByUser(userId));
        }
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseResponseDto> findById(@PathVariable Integer id,
                                                       @RequestHeader Integer userId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(expenseService.findAllRelationsByUser(userId)
                        .stream().filter(expense -> expense.getId() == id)
                        .toList().get(0));
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
    public ResponseEntity<ExpenseResponseDto> create(@RequestBody Expense expense) {
        Expense exp = expenseService.save(expense);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(expenseService.findAllRelationsByPk(exp.getId()).get());
    }

    @PutMapping
    public ResponseEntity<Expense> update(@RequestBody Expense expense) {
        return ResponseEntity.ok(expenseService.update(expense));
    }
}
