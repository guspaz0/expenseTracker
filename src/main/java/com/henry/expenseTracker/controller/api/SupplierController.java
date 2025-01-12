package com.henry.expenseTracker.controller.api;

import com.henry.expenseTracker.Dto.request.SupplierRequestDto;
import com.henry.expenseTracker.Dto.response.SupplierResponseDto;
import com.henry.expenseTracker.exceptions.ErrorsResponse;
import com.henry.expenseTracker.service.impl.SupplierService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="Suppliers")
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/supplier")
public class SupplierController {

    private final SupplierService supplierService;

    @Operation(summary="List all suppliers")
    @GetMapping
    public ResponseEntity<List<SupplierResponseDto>> findAll() {
        return ResponseEntity.ok(supplierService.findAll());
    }

    @Operation(summary="Get Supplier by id")
    @SneakyThrows
    @GetMapping("/{id}")
    public ResponseEntity<SupplierResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(supplierService.findById(id));
    }

    @Operation(summary="Delete Supplier by id")
    @SneakyThrows
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
            return ResponseEntity.ok(supplierService.delete(id));
    }
    @Operation(summary="Update supplier")
    @SneakyThrows
    @PutMapping
    public ResponseEntity<SupplierResponseDto> update(@RequestBody SupplierRequestDto supplier) {
        return ResponseEntity.ok(supplierService.update(supplier));
    }

    @Operation(summary="Create supplier")
    @ApiResponse(
            responseCode = "400",
            description = "when a field is missing or invalid we reponse this",
            content = {
                    @Content(mediaType = "application/json", schema=@Schema(implementation = ErrorsResponse.class))
            }
    )
    @PostMapping
    public ResponseEntity<SupplierResponseDto> save(@RequestBody SupplierRequestDto supplier) {
        log.info("body: "+supplier.toString());
        return ResponseEntity.ok(supplierService.save(supplier));
    }
}
