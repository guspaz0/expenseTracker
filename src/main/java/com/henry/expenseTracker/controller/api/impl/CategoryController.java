package com.henry.expenseTracker.controller.api.impl;

import com.henry.expenseTracker.controller.api.IController;
import com.henry.expenseTracker.entity.Category;
import com.henry.expenseTracker.service.impl.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/category")
public class CategoryController implements IController<Category> {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        Category category = categoryService.findById(id).orElse(null);
        return ResponseEntity.status(HttpStatus.OK).body(category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            categoryService.delete(id);
            return ResponseEntity.ok("Category successfully deleted");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Error deleting Category");
        }
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody Category category) {
        try {
            categoryService.update(category);
            return ResponseEntity.ok("Category updated successfully");
        } catch(Exception e){
            return ResponseEntity.ok("Error updating Category");
        }
    }

    @PostMapping
    public ResponseEntity<Category> save(@RequestBody Category category) {
        log.info("Creando categoria nueva...");
        return ResponseEntity.ok(categoryService.save(category));
    }
}
