package com.henry.expenseTracker.service.impl;

import com.henry.expenseTracker.Dto.request.ExpenseRequestDto;
import com.henry.expenseTracker.Dto.request.ExpirationRequestDto;
import com.henry.expenseTracker.Dto.response.CategoryResponseDto;
import com.henry.expenseTracker.Dto.response.ExpenseResponseDto;
import com.henry.expenseTracker.Dto.response.ExpirationResponseDto;
import com.henry.expenseTracker.Dto.response.SupplierResponseDto;
import com.henry.expenseTracker.entity.jpa.Category;
import com.henry.expenseTracker.entity.jpa.Expense;
import com.henry.expenseTracker.entity.jpa.Supplier;
import com.henry.expenseTracker.exceptions.ExpenseException;
import com.henry.expenseTracker.repository.jpa.ExpenseRepository;
import com.henry.expenseTracker.service.abstract_service.IExpenseService;
import com.henry.expenseTracker.util.constants.CacheConstants;
import com.henry.expenseTracker.util.constants.SortType;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class ExpenseService implements IExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ExpirationsService expirationsService;

    @Override
    //@Cacheable(value= CacheConstants.EXPENSE_CACHE_NAME)
    public List<ExpenseResponseDto> findAll(Integer page, Integer size, SortType sortType) {
        log.info("Listando todas las expensas");
        //var pagination = pageRequest(page, size, sortType);
        //log.info(pagination.toString());
        return this.expenseRepository.findAll().stream()
                .map(this::mapToDTO).toList();
    }

    private PageRequest pageRequest(Integer page, Integer size, SortType sortType) {
        return switch (sortType) {
            case NONE -> PageRequest.of(page, size);
            case LOWER -> PageRequest.of(page,size, Sort.by(FIELD_BY_SORT).ascending());
            case UPPER -> PageRequest.of(page,size, Sort.by(FIELD_BY_SORT).descending());
        };
    }

    @Override
    public ExpenseResponseDto save(ExpenseRequestDto expenseRequestDto) {
        log.info("Creando nueva expensa");
        if (!expenseRequestDto.getExpirations().isEmpty()) {
            checkExpirations(expenseRequestDto.getExpirations());
            expenseRequestDto.setExpires(1);
        } else { expenseRequestDto.setExpires(0); }
        log.info("pasa por linea 53");
        Expense expense = this.expenseRepository.save(mapToEntity(expenseRequestDto));
        log.info("pasa por linea 55");
        ExpenseResponseDto response = mapToDTO(expense);
        if (!expenseRequestDto.getExpirations().isEmpty()) {
            log.info("entra por el if");
            response.setExpirations(
                expenseRequestDto.getExpirations().stream().map(expiration-> {
                    expiration.setExpenseId(expense.getId());
                    var expirationResponse = this.expirationsService.save(expiration);
                    expirationResponse.setAmount(expense.getAmount()*expiration.getParticipation());
                    return expirationResponse;
                }).toList()
            );
        }
        return response;
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
        var response = ExpenseResponseDto.builder()
                .id(expense.getId())
                .description(expense.getDescription())
                .emitDate(expense.getEmitDate())
                .amount(expense.getAmount())
                .currency(expense.getCurrency())
                .category(
                        CategoryResponseDto.builder()
                                .id(expense.getCategory().getId())
                                .name(expense.getCategory().getName())
                                .description(expense.getCategory().getDescription())
                                .build()
                )
                .expires(expense.getExpires())
                .supplier(
                        SupplierResponseDto.builder()
                                .id(expense.getSupplier().getId())
                                .name(expense.getSupplier().getName())
                                .build()
                )
                .userId(expense.getUserId())
                .build();
        if (expense.getExpirations() != null) {
            response.setExpirations(
                expense.getExpirations()
                    .stream().map(expiration ->
                            ExpirationResponseDto.builder()
                                .id(expiration.getId())
                                .expenseId(expiration.getExpenseId())
                                .participation(expiration.getParticipation())
                                .amount(expiration.getParticipation()*expense.getAmount())
                                .expireDate(expiration.getExpireDate())
                                .build())
                    .toList()
            );
        }
        return response;
    }

    private Expense mapToEntity(ExpenseRequestDto expense) {
        return Expense.builder()
                .id(expense.getId())
                .description(expense.getDescription())
                .emitDate(expense.getEmitDate())
                .amount(expense.getAmount())
                .currency(expense.getCurrency())
                .category(
                        Category.builder()
                                .id(expense.getCategory())
                                .build()
                )
                .expires(expense.getExpires())
                .supplier(
                        Supplier.builder()
                                .id(expense.getSupplier())
                                .build()
                )
                .userId(expense.getUserId())
                .build();
    }

}
