package com.henry.expenseTracker.service;

import com.henry.expenseTracker.Dto.response.SupplierResponseDto;
import com.henry.expenseTracker.entity.Supplier;
import com.henry.expenseTracker.repository.SupplierRepository;
import com.henry.expenseTracker.service.impl.SupplierService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class SupplierServiceTest {

    @Mock
    private static SupplierRepository supplierRepository;

    @InjectMocks
    private SupplierService supplierService;

    @DisplayName("Test for listing all Suppliers")
    @Test
    void testFindAllSupplier(){
        Supplier supplier = new Supplier(1L,"Supplier de prueba");
        Supplier supplier2 = new Supplier(1L,"Supplier de prueba 2");

        given(supplierRepository.findAll())
                .willReturn(List.of(supplier,supplier2));

        List<SupplierResponseDto>  supplierList = supplierService.findAll();

        //Then
        assertThat(supplierList).isNotNull();
        assertThat(supplierList.size()).isEqualTo(2);
    }

    @DisplayName("Test for find Supplier by Id")
    @Test
    void testFindByIdSupplier() throws Exception {
        Supplier supplier = new Supplier(1L,"Supplier de prueba");
        given(supplierRepository.findById(any(Long.class)))
                .willReturn(Optional.of(supplier));

        SupplierResponseDto supplierResponseDto = supplierService.findById(1L);

        assertThat(supplierResponseDto).isNotNull();
        assertThat(supplierResponseDto.getName()).isEqualTo("Supplier de prueba");
    }
}
