package com.henry.expenseTracker.service;

import com.henry.expenseTracker.Dto.response.CategoryResponseDto;
import com.henry.expenseTracker.entity.jpa.Category;
import com.henry.expenseTracker.repository.jpa.CategoryRepository;
import com.henry.expenseTracker.service.impl.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @Mock
    private static CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;


    @DisplayName("Test for list all Categories")
    @Test
    void testSaveCategory(){
        Category category = new Category(1L,"prueba","descripcion de prueba");
        Category category2 = new Category(1L,"categoria de prueba", "descripcion de category de prueba");

        given(categoryRepository.findAll())
                .willReturn(List.of(category,category2));

        List<CategoryResponseDto> categoryList = categoryService.findAll();

        //Then
        assertThat(categoryList).isNotNull();
        assertThat(categoryList.size()).isEqualTo(2);
    }

    @DisplayName("Test for find Category by Id")
    @Test
    void testFindByIdCategory() throws Exception {

        Category category = new Category(1L,"prueba","descripcion de prueba");
        given(categoryRepository.findById(any(Long.class)))
                .willReturn(Optional.of(category));

        CategoryResponseDto categoryResponseDto = categoryService.findById(1L);

        assertThat(categoryResponseDto).isNotNull();
        assertThat(categoryResponseDto.getName()).isEqualTo("prueba");
    }
}
