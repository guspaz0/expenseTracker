package com.henry.expenseTracker.service.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.henry.expenseTracker.Dto.request.SupplierRequestDto;
import com.henry.expenseTracker.Dto.response.SupplierResponseDto;
import com.henry.expenseTracker.entity.Supplier;
import com.henry.expenseTracker.repository.SupplierRepository;
import com.henry.expenseTracker.service.ISupplierService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService implements ISupplierService {
    private final SupplierRepository supplierRepository;
    private final ObjectMapper objectMapper;

    public SupplierService(SupplierRepository supplierRepository, ObjectMapper objectMapper){
        this.supplierRepository = supplierRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<SupplierResponseDto> findAll() {
        return supplierRepository.findAll()
                .stream().map(this::mapToDTO)
                .toList();
    }

    @Override
    public SupplierResponseDto save(SupplierRequestDto supplier) {
        return mapToDTO(supplierRepository.save(mapToEntity(supplier)));
    }

    @Override
    public SupplierResponseDto findById(Long id) throws Exception {
        return mapToDTO(
                supplierRepository.findById(id)
                .orElseThrow(()-> new Exception("Supplier id: "+id+" not found"))
        );
    }

    @Override
    public SupplierResponseDto update(SupplierRequestDto supplier) throws Exception {
        this.findById(supplier.getId());
        return mapToDTO(supplierRepository.save(mapToEntity(supplier)));
    }

    @Override
    public String delete(Long id) throws Exception {
        this.findById(id);
        supplierRepository.deleteById(id);
        return "Supplier id: "+id+" deleted successfully";
    }

    private SupplierResponseDto mapToDTO(Supplier payment) {
        return objectMapper.convertValue(payment, SupplierResponseDto.class);
    }

    private Supplier mapToEntity(SupplierRequestDto supplierRequestDTO) {
        return objectMapper.convertValue(supplierRequestDTO, Supplier.class);
    }
}
