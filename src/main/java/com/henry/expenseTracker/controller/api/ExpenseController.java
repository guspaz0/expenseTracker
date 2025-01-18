package com.henry.expenseTracker.controller.api;

import com.henry.expenseTracker.Dto.request.ExpenseRequestDto;
import com.henry.expenseTracker.Dto.response.ExpenseResponseDto;
import com.henry.expenseTracker.exceptions.ErrorResponse;
import com.henry.expenseTracker.exceptions.ErrorsResponse;
import com.henry.expenseTracker.service.impl.ExpenseService;
import com.henry.expenseTracker.util.constants.SortType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="Expenses")
@Slf4j
@RestController
@RequestMapping("/api/expense")
public class ExpenseController {
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @Operation(summary="List all expenses")
    @GetMapping
    public ResponseEntity<List<ExpenseResponseDto>> findAll(
        @RequestParam(required = false, defaultValue = "1") String page,
        @RequestParam(required = false, defaultValue = "100") String size,
        @RequestHeader(required=false, defaultValue = "NONE") SortType sortType
    ) {
        var authencation = SecurityContextHolder.getContext().getAuthentication();
        log.info("Authentication: "+ authencation.toString());
        return ResponseEntity.ok(expenseService.findAll(
                Integer.parseInt(page), Integer.parseInt(size), sortType));
    }

    @Operation(summary="List expense indentified by id params")
    @SneakyThrows
    @GetMapping("/{id}")
    public ResponseEntity<ExpenseResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(expenseService.findById(id));
    }

    @Operation(summary="Delete expense by id")
    @SneakyThrows
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return ResponseEntity.ok(expenseService.delete(id));
    }

    @Operation(summary="Update expense")
    @SneakyThrows
    @PutMapping
    public ResponseEntity<ExpenseResponseDto> update(@Valid @RequestBody ExpenseRequestDto expense) {
        return new ResponseEntity<>(expenseService.update(expense), HttpStatus.CREATED);
    }

    @ApiResponse(
            responseCode = "400",
            description = "when a field is missing or invalid the response is this",
            content = {
                    @Content(mediaType = "application/json", schema=@Schema(implementation = ErrorsResponse.class))
            }
    )
    @Operation(summary="Create new expense")
    @PostMapping
    public ResponseEntity<ExpenseResponseDto> save(@Valid @RequestBody ExpenseRequestDto expense) {
        //log.info("Body: "+ expense.toString());
        return new ResponseEntity<>(expenseService.save(expense), HttpStatus.CREATED);
    }
}
