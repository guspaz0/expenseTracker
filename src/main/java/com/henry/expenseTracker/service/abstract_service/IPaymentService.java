package com.henry.expenseTracker.service.abstract_service;

import com.henry.expenseTracker.Dto.request.ExpirationPaymentRequestDto;
import com.henry.expenseTracker.Dto.request.PaymentRequestDto;
import com.henry.expenseTracker.Dto.response.PaymentResponseDto;
import com.henry.expenseTracker.entity.jpa.ExpirationPayments;

import java.util.List;

public interface IPaymentService {

    List<PaymentResponseDto> findAll();

    /**
     * @param t Object com.henry.expenseTracker.entity you want to add in database
     * @return Object created
     */
    PaymentResponseDto save(PaymentRequestDto t) throws Exception;

    /**
     * @param id identifier of Object com.henry.expenseTracker.entity you want to search
     * @return Object com.henry.expenseTracker.entity
     */
    PaymentResponseDto findById(Long id) throws Exception;

    /**
     * List of Objects com.henry.expenseTracker.entity stored in database
     * @param id identifier of Object com.henry.expenseTracker.entity you want to delete
     */
    String delete(Long id) throws Exception;

    /**
     * @param t Object to be updated
     * @return Object updated
     */
    PaymentResponseDto update(PaymentRequestDto t) throws Exception;

    List<ExpirationPayments> savePaymentExpiration(Long paymentId, List<ExpirationPaymentRequestDto> t) throws Exception;

}
