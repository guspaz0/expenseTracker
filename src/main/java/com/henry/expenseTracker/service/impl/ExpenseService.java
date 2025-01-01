package com.henry.expenseTracker.service.impl;

import com.henry.expenseTracker.Dto.request.ExpenseRequestDto;
import com.henry.expenseTracker.Dto.request.ExpirationRequestDto;
import com.henry.expenseTracker.Dto.response.CategoryResponseDto;
import com.henry.expenseTracker.Dto.response.ExpenseResponseDto;
import com.henry.expenseTracker.Dto.response.ExpirationResponseDto;
import com.henry.expenseTracker.Dto.response.SupplierResponseDto;
import com.henry.expenseTracker.entity.Category;
import com.henry.expenseTracker.entity.Expense;
import com.henry.expenseTracker.entity.Expiration;
import com.henry.expenseTracker.entity.Supplier;
import com.henry.expenseTracker.exceptions.ExpenseException;
import com.henry.expenseTracker.repository.ExpenseRepository;
import com.henry.expenseTracker.repository.ExpirationRepository;
import com.henry.expenseTracker.service.IExpenseService;
import jakarta.transaction.Transactional;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//@Transactional(propagation=Propagation.NESTED)
@Slf4j
@Service
public class ExpenseService implements IExpenseService {
    private final ExpenseRepository expenseRepository;
    private final ExpirationRepository expirationRepository;
    private final Jackson2ObjectMapperBuilder objectMapper = new Jackson2ObjectMapperBuilder();

    public ExpenseService(ExpenseRepository expenseRepository,
                          ExpirationRepository expirationRepository) {
        this.expenseRepository = expenseRepository;
        this.expirationRepository = expirationRepository;
    }

    @Override
    public List<ExpenseResponseDto> findAll() {
        log.info("Listando todas las expensas");
        return expenseRepository.findAll()
                .stream().map(this::mapToDTO)
                .toList();
    }


    @Override
    public ExpenseResponseDto save(ExpenseRequestDto expenseRequestDto) {
        log.info("Creando nueva expensa");
        if (!expenseRequestDto.getExpirations().isEmpty()) {
            checkExpirations(expenseRequestDto.getExpirations());
            expenseRequestDto.setExpires(1);
        } else { expenseRequestDto.setExpires(0); }
        Expense expense = mapToEntity(expenseRequestDto);
        expense.setExpirations(new ArrayList<>());
        expense = expenseRepository.save(expense);
        Expense finalExpense = expense;
        if (!expenseRequestDto.getExpirations().isEmpty()) {
            log.info("{}",expenseRequestDto.getExpirations().toString());
            finalExpense.setExpirations(expenseRequestDto.getExpirations()
                    .stream().map(expiration -> {
                        expiration.setExpenseId(finalExpense.getId());
                        return expirationRepository.save(mapToEntity(expiration));
                    }).toList());
        }
        return mapToDTO(finalExpense);
    }

    @SneakyThrows
    @Override
    public ExpenseResponseDto findById(Long id) {
        return mapToDTO(expenseRepository.findById(id)
                .orElseThrow(()-> new Exception("Expense id: "+id+" not found")));
    }

    @Override
    public String delete(Long id) throws Exception {
        this.findById(id);
        expenseRepository.deleteById(id);
        log.info("Borrando expensa id: {}",id);
        return "Expense id: "+id+" deleted successfully";
    }

    @Override
    public ExpenseResponseDto update(ExpenseRequestDto expense) throws Exception {
        this.findById(expense.getId());
        return mapToDTO(expenseRepository.save(mapToEntity(expense)));
    }

    @SneakyThrows
    public void checkExpirations(List<ExpirationRequestDto> expiration) {
        double sumParticipation = expiration.parallelStream()
                .reduce(0.00,(cum, elem) ->
                        cum + elem.getParticipation(), Double::sum);
        if (sumParticipation != 1) {
            log.error("inconsistencia al checkear los vencimientos de la nueva expensa");
            throw new ExpenseException("total sum of expense expiration participation is not equal than 1.0");
        }
    }

    private ExpenseResponseDto mapToDTO(Expense expense) {
        List<ExpirationResponseDto> expirationsList = expense.getExpirations()
                .stream().map(expiration ->
                        ExpirationResponseDto.builder()
                                .id(expiration.getId())
                                .expenseId(expiration.getExpenseId())
                                .participation(expiration.getParticipation())
                                .expireDate(expiration.getExpireDate())
                                .build())
                .toList();
        return ExpenseResponseDto.builder()
                .id(expense.getId())
                .description(expense.getDescription())
                .emitDate(expense.getEmitDate())
                .amount(expense.getAmount())
                .category(
                    CategoryResponseDto.builder()
                        .id(expense.getCategory().getId())
                        .name(expense.getCategory().getName())
                        .description(expense.getCategory().getDescription())
                        .build()
                )
                .expires(expense.getExpires())
                .expirations(expirationsList)
                .supplier(
                    SupplierResponseDto.builder()
                        .id(expense.getSupplier().getId())
                        .name(expense.getSupplier().getName())
                        .build()
                )
                .userId(expense.getUserId())
                .build();
    }

    private Expense mapToEntity(ExpenseRequestDto expense) {
        return Expense.builder()
                .id(expense.getId())
                .description(expense.getDescription())
                .emitDate(expense.getEmitDate())
                .amount(expense.getAmount())
                .category(
                        Category.builder()
                                .id(expense.getCategory().getId())
                                .name(expense.getCategory().getName())
                                .description(expense.getCategory().getDescription())
                                .build()
                )
                .expires(expense.getExpires())
                .expirations(
                        expense.getExpirations().stream().map(this::mapToEntity).toList())
                .supplier(
                        Supplier.builder()
                                .id(expense.getSupplier().getId())
                                .name(expense.getSupplier().getName())
                                .build()
                )
                .userId(expense.getUserId())
                .build();
    }

    private Expiration mapToEntity(ExpirationRequestDto expiration) {
        return Expiration.builder()
                .id(expiration.getId())
                .expenseId(expiration.getExpenseId())
                .participation(expiration.getParticipation())
                .expireDate(expiration.getExpireDate())
                .build();
    }
}
