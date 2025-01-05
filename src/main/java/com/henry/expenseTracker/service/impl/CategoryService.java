package com.henry.expenseTracker.service.impl;

import com.henry.expenseTracker.Dto.request.CategoryRequestDto;
import com.henry.expenseTracker.Dto.response.CategoryResponseDto;
import com.henry.expenseTracker.entity.Category;
import com.henry.expenseTracker.repository.CategoryRepository;
import com.henry.expenseTracker.service.abstract_service.ICategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryResponseDto> findAll() {
        return categoryRepository.findAll()
                .stream().map(this::mapToDTO)
                .toList();
    }

    @Override
    public CategoryResponseDto findById(Long id) throws Exception {
        return mapToDTO(categoryRepository.findById(id)
                .orElseThrow(() -> new Exception("Category id: "+id+" not found"))
        );
    }

    @Override
    public CategoryResponseDto save(CategoryRequestDto category) {
        return mapToDTO(categoryRepository.save(mapToEntity(category)));
    }

    @Override
    public CategoryResponseDto update(CategoryRequestDto category) throws Exception {
        this.findById(category.getId());
        return mapToDTO(categoryRepository.save(mapToEntity(category)));
    }

    @Override
    public String delete(Long id) throws Exception {
        this.findById(id);
        categoryRepository.deleteById(id);
        return "Expense id: "+id+" deleted successfully";
    }

    private CategoryResponseDto mapToDTO(Category category) {
        return CategoryResponseDto.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();

    }

    private Category mapToEntity(CategoryRequestDto categoryRequestDto) {
        return Category.builder()
                .id(categoryRequestDto.getId())
                .name(categoryRequestDto.getName())
                .description(categoryRequestDto.getDescription())
                .build();
    }
}
