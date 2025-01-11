package com.henry.expenseTracker.service.impl;

import com.henry.expenseTracker.Dto.request.ExpirationRequestDto;
import com.henry.expenseTracker.Dto.response.ExpirationResponseDto;
import com.henry.expenseTracker.entity.jpa.Expiration;
import com.henry.expenseTracker.repository.jpa.ExpenseRepository;
import com.henry.expenseTracker.repository.jpa.ExpirationRepository;
import com.henry.expenseTracker.service.abstract_service.IExpirationsService;
import com.henry.expenseTracker.util.constants.CacheConstants;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@AllArgsConstructor
public class ExpirationsService implements IExpirationsService {

    private final ExpenseRepository expenseRepository;
    private final ExpirationRepository expirationRepository;

    @Override
    @Cacheable(value= CacheConstants.EXPIRATIONS_CACHE_NAME)
    public List<ExpirationResponseDto> findAllByExpenseId(Long Id) {
        return this.expirationRepository.findByExpenseId(Id)
                .stream().map(this::mapToDto)
                .toList();
    }

    @Override
    public ExpirationResponseDto save(ExpirationRequestDto expiration) {
        return mapToDto(this.expirationRepository.save(mapToEntity(expiration)));
    }

    @Override
    public ExpirationResponseDto findById(Long id) throws Exception {
        return mapToDto(this.expirationRepository.findById(id)
                .orElseThrow(()-> new Exception("Expense id: "+id+" not found")));
    }

    @Override
    public String delete(Long id) throws Exception {
        this.findById(id);
        // checkear consistencia antes de borrar
        //this.expirationRepository.deleteById(id);
        return "not implemented";
    }

    @Override
    public ExpirationResponseDto update(ExpirationRequestDto expense) throws Exception {
        return null;
    }

    private Expiration mapToEntity(ExpirationRequestDto expiration) {
        return Expiration.builder()
                .id(expiration.getId())
                .expenseId(expiration.getExpenseId())
                .participation(expiration.getParticipation())
                .expireDate(expiration.getExpireDate())
                .build();
    }

    private ExpirationResponseDto mapToDto(Expiration expiration) {
        //Optional<Expense> expense = expenseRepository.findById(expiration.getExpenseId());
        return ExpirationResponseDto.builder()
                .id(expiration.getId())
                .expenseId(expiration.getExpenseId())
                .participation(expiration.getParticipation())
                .expireDate(expiration.getExpireDate())
                //.amount(expense.get().getAmount()*expiration.getParticipation())
                .build();
    }
}
