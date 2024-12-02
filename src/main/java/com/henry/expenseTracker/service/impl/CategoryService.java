package com.henry.expenseTracker.service.impl;

import com.henry.expenseTracker.entity.Category;
import com.henry.expenseTracker.repository.CategoryRepository;
import com.henry.expenseTracker.service.IService;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements IService<Category> {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void update(Category category) {
        Optional<Category> optionalCategory = this.findById(category.getId());
        if (optionalCategory.isPresent()) {
            categoryRepository.save(category);
        }
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
