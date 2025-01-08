package com.henry.expenseTracker.controller;

import com.henry.expenseTracker.Dto.request.ExpenseRequestDto;
import com.henry.expenseTracker.Dto.request.ExpirationRequestDto;
import com.henry.expenseTracker.Dto.response.CategoryResponseDto;
import com.henry.expenseTracker.Dto.response.ExpenseResponseDto;
import com.henry.expenseTracker.Dto.response.ExpirationResponseDto;
import com.henry.expenseTracker.Dto.response.SupplierResponseDto;
import com.henry.expenseTracker.controller.api.ExpenseController;
import com.henry.expenseTracker.service.impl.ExpenseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ExpenseController.class)
class ExpenseControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExpenseService expenseServiceMock;

    private ExpenseResponseDto sampleExpenseResponse;

    private ExpenseRequestDto sampleExpenseRequest;

    @BeforeEach
    void setUp() {
        sampleExpenseResponse = ExpenseResponseDto.builder()
                .id(1L)
                .description("testing expenses")
                .emitDate(LocalDate.parse("2024-12-31"))
                .amount(100.5)
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
                .category(1L)
                .expires(0)
                .expirations(expirationRequestDtoList)
                .supplier(1L)
                .userId(1L)
                .build();

    }


    @Test
    void getAllExpenses_ReturnsListOfExpenses() throws Exception {
        // GIVEN
        List<ExpenseResponseDto> expenses = Collections.singletonList(sampleExpenseResponse);
        when(expenseServiceMock.findAll()).thenReturn(expenses);

        //WHEN
        //THEN
        mockMvc.perform(
                        get("/api/expense")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath(
                        "$[0].description",
                        is("testing expenses")));
    }

    @Test
    public void testSaveExpense() throws Exception {

        List<ExpirationResponseDto> expirationResponseDtoList = new ArrayList<>();
        expirationResponseDtoList.add(
                ExpirationResponseDto.builder()
                    .id(1L)
                    .expenseId(1L)
                    .expireDate(LocalDate.parse("2024-12-30"))
                    .participation(0.33)
                    .build());
        expirationResponseDtoList.add(ExpirationResponseDto.builder()
                .id(2L)
                .expenseId(1L)
                .expireDate(LocalDate.parse("2025-01-30"))
                .participation(0.33)
                .build());
        expirationResponseDtoList.add(ExpirationResponseDto.builder()
                .id(3L)
                .expenseId(1L)
                .expireDate(LocalDate.parse("2025-02-28"))
                .participation(0.34)
                .build());

        ExpenseResponseDto expenseResponseDto = new ExpenseResponseDto(
            1L,"Probando post expensas",100.5,"ARS",
            LocalDate.parse("2024-12-01"),1,
            new CategoryResponseDto(1L, null, null),
            new SupplierResponseDto(1L,null),
            1L, //userI
            expirationResponseDtoList
        );

        //GIVEN
        given(expenseServiceMock.save(any(ExpenseRequestDto.class)))
                .willReturn(expenseResponseDto);

        //WHEN + THEN
        mockMvc.perform(post("/api/expense")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Jackson2ObjectMapperBuilder().build().writeValueAsString(sampleExpenseRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.description").value("Probando post expensas"));
    }

}