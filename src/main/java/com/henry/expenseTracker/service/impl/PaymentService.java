package com.henry.expenseTracker.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.henry.expenseTracker.Dto.request.ExpirationPaymentRequestDto;
import com.henry.expenseTracker.Dto.request.PaymentRequestDto;
import com.henry.expenseTracker.Dto.response.PaymentResponseDto;
import com.henry.expenseTracker.entity.ExpirationPayments;
import com.henry.expenseTracker.entity.Payment;
import com.henry.expenseTracker.entity.Supplier;
import com.henry.expenseTracker.exceptions.PaymentException;
import com.henry.expenseTracker.repository.ExpirationPaymentsRepository;
import com.henry.expenseTracker.repository.PaymentRepository;
import com.henry.expenseTracker.service.IPaymentService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class PaymentService implements IPaymentService {
    private final PaymentRepository paymentRepository;
    private final ExpirationPaymentsRepository expirationPaymentsRepository;
    private final ObjectMapper objectMapper;

    public PaymentService(PaymentRepository paymentRepository,
                          ExpirationPaymentsRepository expirationPayments,
                          ObjectMapper objectMapper) {
        this.paymentRepository = paymentRepository;
        this.expirationPaymentsRepository = expirationPayments;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<PaymentResponseDto> findAll() {
        return paymentRepository.findAll().stream()
                .map(this::mapToDTO)
                .toList();
    }

    @SneakyThrows
    @Override
    public PaymentResponseDto findById(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(()-> new Exception("Payment id: "+id+"not found"));
        return mapToDTO(payment);
    }

    @Override
    public PaymentResponseDto save(PaymentRequestDto paymentRequest) throws Exception {
        Payment payRequest = new Payment();
        payRequest.setAmount(paymentRequest.getAmount());
        payRequest.setDate(paymentRequest.getDate());
        Supplier supplier = new Supplier();
        supplier.setId(paymentRequest.getSupplierId());
        payRequest.setSupplier(supplier);
        payRequest.setUser_id(paymentRequest.getUserId());

        Payment newPayment = paymentRepository.save(payRequest);
        newPayment.setExpirations(
                savePaymentExpiration(
                        newPayment.getId(),
                        paymentRequest.getExpirations()
                )
        );

        return mapToDTO(newPayment);
    }

    @Override
    public PaymentResponseDto update(PaymentRequestDto payment) {
        this.findById(payment.getId());
        return mapToDTO(paymentRepository.save(mapToEntity(payment)));
    }

    @Override
    public String delete(Long id) {
        this.findById(id);
        paymentRepository.deleteById(id);
        return "Payment id: "+id+" deleted successfully";
    }

    @Override
    public List<ExpirationPayments> savePaymentExpiration(
            Long payment_id,
            List<ExpirationPaymentRequestDto> paymentRequest) throws Exception {
        try {
            List<ExpirationPayments> expPaymentList = new ArrayList<ExpirationPayments>();
            paymentRequest
                .forEach(expirationPayments -> {
                    ExpirationPayments expPayment = mapToEntity(expirationPayments);
                    expPayment.setPaymentId(payment_id);
                    checkNotExceeded(expPayment);
                    expPaymentList.add(expirationPaymentsRepository.save(expPayment));
                });
            return expPaymentList;
        } catch (Exception e) {
            throw new PaymentException(e.getMessage());
        }
    }

    private void checkNotExceeded(ExpirationPayments expirationPayments) throws RuntimeException {
        log.info("Checking if payments not exceeds limit variables");
        List<ExpirationPayments> expirationsById = expirationPaymentsRepository.findByExpirationId(expirationPayments.getExpirationId());
        double sumExpirationPart = expirationsById.parallelStream()
                .reduce(expirationPayments.getExpirationPart(),(cum, elem) ->
                        cum + elem.getExpirationPart(), Double::sum);

        List<ExpirationPayments> paymentsById = expirationPaymentsRepository.findByPaymentId(expirationPayments.getPaymentId());
        double sumPaymentPart = paymentsById.parallelStream()
                .reduce(expirationPayments.getPaymentPart(),(cum, elem) ->
                        cum + elem.getPaymentPart(), Double::sum);
        if (sumExpirationPart > 1 || sumPaymentPart > 1) {
            throw new RuntimeException("La participacion del pago excede el monto disponible");
        }
    }

    private PaymentResponseDto mapToDTO(Payment payment) {
        return objectMapper.convertValue(payment, PaymentResponseDto.class);
    }

    private Payment mapToEntity(PaymentRequestDto paymentRequestDTO) {
        return objectMapper.convertValue(paymentRequestDTO, Payment.class);
    }
    private ExpirationPayments mapToEntity(ExpirationPaymentRequestDto paymentRequestDTO) {
        return objectMapper.convertValue(paymentRequestDTO, ExpirationPayments.class);
    }

}
