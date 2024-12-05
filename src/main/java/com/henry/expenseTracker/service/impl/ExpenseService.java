package com.henry.expenseTracker.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.henry.expenseTracker.Dto.request.ExpenseRequestDto;
import com.henry.expenseTracker.Dto.request.ExpirationRequestDto;
import com.henry.expenseTracker.Dto.response.ExpenseResponseDto;
import com.henry.expenseTracker.entity.Expense;
import com.henry.expenseTracker.entity.Expiration;
import com.henry.expenseTracker.repository.ExpenseRepository;
import com.henry.expenseTracker.repository.ExpirationRepository;
import com.henry.expenseTracker.service.IExpenseService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ExpenseService implements IExpenseService {
    private final ExpenseRepository expenseRepository;
    private final ExpirationRepository expirationRepository;
    private final ObjectMapper objectMapper;

    public ExpenseService(ExpenseRepository expenseRepository,
                          ExpirationRepository expirationRepository,
                          ObjectMapper objectMapper) {
        this.expenseRepository = expenseRepository;
        this.expirationRepository = expirationRepository;
        this.objectMapper = objectMapper;
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
        checkExpirations(expenseRequestDto.getExpirations());
        Expense expense = mapToEntity(expenseRequestDto);
        expense.setExpirations(new ArrayList<>());
        expense = expenseRepository.save(expense);
        Expense finalExpense = expense;
        List<Expiration> expirationList =  expenseRequestDto.getExpirations()
                .stream().map(expiration -> {
                        expiration.setExpenseId(finalExpense.getId());
                        return expirationRepository.save(mapToEntity(expiration));
                }).toList();
        finalExpense.setExpirations(expirationList);
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
            log.warn("inconsistencia al checkear los vencimientos de la nueva expensa");
            throw new RuntimeException("total sum of expense expiration participation is not equal than 1.0");
        }
    }

    private ExpenseResponseDto mapToDTO(Expense expense) {
        return objectMapper.convertValue(expense, ExpenseResponseDto.class);
    }

    private Expense mapToEntity(ExpenseRequestDto expenseRequestDto) {
        return objectMapper.convertValue(expenseRequestDto, Expense.class);
    }

    private Expiration mapToEntity(ExpirationRequestDto expirationRequestDto) {
        return objectMapper.convertValue(expirationRequestDto, Expiration.class);
    }
}
