package com.henry.expenseTracker.service.impl;


import com.henry.expenseTracker.Dto.request.SupplierRequestDto;
import com.henry.expenseTracker.Dto.response.SupplierResponseDto;
import com.henry.expenseTracker.entity.Supplier;
import com.henry.expenseTracker.repository.SupplierRepository;
import com.henry.expenseTracker.service.abstract_service.ISupplierService;
import com.henry.expenseTracker.util.constants.CacheConstants;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SupplierService implements ISupplierService {

    private final SupplierRepository supplierRepository;

    @Override
    @Cacheable(value= CacheConstants.SUPPLIER_CACHE_NAME)
    public List<SupplierResponseDto> findAll() {
        // para simular un cuello de botella en la red
//        try {
//            Thread.sleep(7000);
//        } catch(InterruptedException e){
//            throw new RuntimeException(e);
//        }
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

    private SupplierResponseDto mapToDTO(Supplier supplier) {
        return SupplierResponseDto.builder()
                .id(supplier.getId())
                .name(supplier.getName())
                .build();
    }

    private Supplier mapToEntity(SupplierRequestDto supplierRequestDTO) {
        return Supplier.builder()
                .id(supplierRequestDTO.getId())
                .name(supplierRequestDTO.getName())
                .build();
    }
}
