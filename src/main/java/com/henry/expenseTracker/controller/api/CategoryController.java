package com.henry.expenseTracker.controller.api;

import com.henry.expenseTracker.Dto.request.CategoryRequestDto;
import com.henry.expenseTracker.Dto.response.CategoryResponseDto;
import com.henry.expenseTracker.exceptions.ErrorsResponse;
import com.henry.expenseTracker.service.impl.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="Category")
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Operation(summary="List all Categories")
    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> findAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @Operation(summary="Get category identified by id param")
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> findById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(categoryService.findById(id));
    }

    @Operation(summary="Delete category by id param")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(categoryService.delete(id));
    }

    @Operation(summary="Update Category")
    @PutMapping
    public ResponseEntity<CategoryResponseDto> update(@Valid @RequestBody CategoryRequestDto category) throws Exception {
        return ResponseEntity.ok(categoryService.update(category));
    }

    @ApiResponse(
            responseCode = "400",
            description = "when a field is missing or invalid we reponse this",
            content = {
                    @Content(mediaType = "application/json", schema=@Schema(implementation = ErrorsResponse.class))
            }
    )
    @Operation(summary="Create Category")
    @PostMapping
    public ResponseEntity<CategoryResponseDto> save(@Valid @RequestBody CategoryRequestDto category) {
        return ResponseEntity.ok(categoryService.save(category));
    }
}
