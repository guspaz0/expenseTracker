package com.henry.expenseTracker.service;

import com.henry.expenseTracker.Dto.request.SupplierRequestDto;
import com.henry.expenseTracker.Dto.response.SupplierResponseDto;

import java.util.List;

public interface ISupplierService {
    List<SupplierResponseDto> findAll();

    SupplierResponseDto save(SupplierRequestDto supplier);

    SupplierResponseDto findById(Long id) throws Exception;

    SupplierResponseDto update(SupplierRequestDto supplier) throws Exception;

    String delete(Long id) throws Exception;
}
