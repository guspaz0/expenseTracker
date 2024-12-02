package com.henry.expenseTracker.controller.api.impl;

import com.henry.expenseTracker.controller.api.Dto.ExpenseResponseDto;
import com.henry.expenseTracker.entity.Expense;
import com.henry.expenseTracker.service.impl.ExpenseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/expenses/old")
public class ExpenseControllerOld {

    private final ExpenseService expenseService;

    public ExpenseControllerOld(ExpenseService expenseService) { this.expenseService = expenseService;}

//    @GetMapping
//    public ResponseEntity<List<ExpenseResponseDto>> getAll(@RequestHeader Integer userId) {
//        log.info("[GET:/api/expenses] Lista de Expensas...");
//        log.debug("probando esto es un log de debug");
//        log.warn("probando esto es una advertencia");
//
//        ResponseEntity<List<ExpenseResponseDto>> response = null;
//        if (userId.describeConstable().isPresent()) {
//            response = ResponseEntity.status(HttpStatus.OK).body(expenseService.findAllRelationsByUser(userId));
//        }
//        return response;
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<ExpenseResponseDto> findById(@PathVariable Long id,
//                                                       @RequestHeader Long userId) {
//        ExpenseResponseDto expenseResponse = expenseService.findAllRelationsByUser(userId)
//            .stream().filter(expense -> expense.getId() == id)
//            .toList().get(0);
//        log.info("El usuario Id={} ah accedido al Expense Id={}", userId, id);
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(expenseResponse);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteById(@PathVariable Long id) {
//        ResponseEntity<String> response = null;
//        if (expenseService.findById(id).isPresent()) {
//            expenseService.delete(id);
//            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted");
//        } else {
//            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//        return response;
//    }
//
//    @PostMapping
//    public ResponseEntity<ExpenseResponseDto> create(@RequestBody Expense expense) {
//        log.info("[POST:/api/expenses] Creando Expensa nueva...");
//        Expense exp = expenseService.save(expense);
//        return ResponseEntity.status(HttpStatus.CREATED)
//                .body(expenseService.findAllRelationsByPk(exp.getId()).get());
//    }
//
//    @PutMapping
//    public ResponseEntity<Expense> update(@RequestBody Expense expense) {
//        return (ResponseEntity<Expense>) ResponseEntity.ok();
//    }
}
