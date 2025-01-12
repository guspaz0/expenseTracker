package com.henry.expenseTracker.service;

import com.henry.expenseTracker.Dto.request.ExpenseRequestDto;
import com.henry.expenseTracker.Dto.request.ExpirationRequestDto;
import com.henry.expenseTracker.Dto.response.CategoryResponseDto;
import com.henry.expenseTracker.Dto.response.ExpenseResponseDto;
import com.henry.expenseTracker.Dto.response.SupplierResponseDto;
import com.henry.expenseTracker.entity.jpa.*;
import com.henry.expenseTracker.repository.jpa.ExpenseRepository;
import com.henry.expenseTracker.repository.jpa.ExpirationRepository;
import com.henry.expenseTracker.service.impl.ExpenseService;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class ExpenseServiceTest {

    @Mock
    private ExpenseRepository expenseRepository;
    @Mock
    private ExpirationRepository expirationRepository;

    @InjectMocks
    private ExpenseService expenseService; /* = new ExpenseService(expenseRepository, expirationRepository, objectMapper);*/


    private ExpenseResponseDto sampleExpenseResponse;
    private ExpenseRequestDto sampleExpenseRequest;

    @BeforeEach
    void setUp() {

        Supplier supplier = new Supplier(1L,"supplier de prueba");
        Category category = new Category(1L,"categoria de prueba","descripcion de prueba");
        User user = new User(null,"Jhon Doe","jhon.doe@asd.com","AR","ARS", UserRole.ROLE_ADMIN,"1234");

        sampleExpenseResponse = ExpenseResponseDto.builder()
                .id(1L)
                .description("testing expenses")
                .emitDate(LocalDate.parse("2024-12-31"))
                .amount(100.5)
                .currency("ARS")
                .category(new CategoryResponseDto(1L,null,null))
                .expires(0)
                .expirations(new ArrayList<>())
                .supplier(new SupplierResponseDto(1L,null))
                .userId(1L)
                .build();

        List<ExpirationRequestDto> expirationRequestDtoList = new ArrayList<>();
        expirationRequestDtoList.add(new ExpirationRequestDto(null,null,LocalDate.parse("2024-12-30"),0.33));
        expirationRequestDtoList.add(new ExpirationRequestDto(null,null,LocalDate.parse("2025-02-28"),0.34));
        expirationRequestDtoList.add(new ExpirationRequestDto(null,null,LocalDate.parse("2025-01-30"),0.33));

        sampleExpenseRequest = ExpenseRequestDto.builder()
                .description("Probando post expensas")
                .emitDate(LocalDate.parse("2024-12-01"))
                .amount(100.5)
                .currency("ARS")
                .category(category.getId())
                .expires(0)
                .expirations(expirationRequestDtoList)
                .supplier(supplier.getId())
                .userId(1L)
                .build();

    }

    @DisplayName("List all Expenses")
    @Test
    void testListAllExpenses() {

        Expense expense = Expense.builder()
                .id(null)
                .description("Probando post expensas")
                .amount(100.5)
                .currency("ARS")
                .emitDate(LocalDate.parse("2024-12-01"))
                .category(new Category(1L,null,null))
                .supplier(new Supplier(1L, null))
                .userId(1L)
                .expires(0)
                .expirations(new ArrayList<>())
                .build();

        given(expenseRepository.findAll())
                .willReturn(List.of(expense,expense));
        List<ExpenseResponseDto> expenseList = expenseService.findAll();

        assertThat(expenseList).isNotNull();
        assertThat(expenseList.size()).isEqualTo(2);

    }

    @DisplayName("Find Expense By Id")
    @Test
    void testFindByIdExpense() {
        Supplier supplier = new Supplier(1L,"supplier de prueba");
        Category category = new Category(1L,"categoria de prueba","descripcion de prueba");
        User user = new User(null,"Jhon Doe","jhon.doe@asd.com","AR","ARS", UserRole.ROLE_ADMIN,"1234");

        Expense expense = Expense.builder()
                .id(1L)
                .description("Probando post expensas")
                .amount(100.5)
                .currency("ARS")
                .emitDate(LocalDate.parse("2024-12-01"))
                .category(category)
                .supplier(supplier)
                .userId(1L)
                .expires(0)
                .expirations(new ArrayList<>())
                .build();

        given(expenseRepository.findById(any(Long.class))).willReturn(Optional.of(expense));

        ExpenseResponseDto expenseReponseDto = expenseService.findById(1L);

        assertThat(expenseReponseDto).isNotNull();
    }

}
