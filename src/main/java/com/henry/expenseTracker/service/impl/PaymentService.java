package com.henry.expenseTracker.service.impl;

import com.henry.expenseTracker.Dto.request.ExpirationPaymentRequestDto;
import com.henry.expenseTracker.Dto.request.PaymentRequestDto;
import com.henry.expenseTracker.Dto.response.ExpirationPaymentResponseDto;
import com.henry.expenseTracker.Dto.response.PaymentResponseDto;
import com.henry.expenseTracker.entity.jpa.ExpirationPayments;
import com.henry.expenseTracker.entity.jpa.Payment;
import com.henry.expenseTracker.entity.jpa.Supplier;
import com.henry.expenseTracker.exceptions.PaymentException;
import com.henry.expenseTracker.repository.jpa.ExpirationPaymentsRepository;
import com.henry.expenseTracker.repository.jpa.PaymentRepository;
import com.henry.expenseTracker.service.abstract_service.IPaymentService;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import java.util.ArrayList;
import java.util.List;

@Transactional(propagation= Propagation.NESTED)
@Slf4j
@Service
@AllArgsConstructor
public class PaymentService implements IPaymentService {

    private final PaymentRepository paymentRepository;
    private final ExpirationPaymentsRepository expirationPaymentsRepository;

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
        if (!paymentRequest.getExpirations().isEmpty()) {
            newPayment.setExpirations(
                    savePaymentExpiration(
                            newPayment.getId(),
                            paymentRequest.getExpirations()
                    )
            );
        }
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
            log.error("La participacion del pago excede el monto disponible");
            throw new RuntimeException("La participacion del pago excede el monto disponible");
        }
    }


    private PaymentResponseDto mapToDTO(Payment payment) {
        return PaymentResponseDto.builder()
                .id(payment.getId())
                .date(payment.getDate())
                .amount(payment.getAmount())
                .userId(payment.getUser_id())
                .supplierId(payment.getSupplier().getId())
                .expirations(payment.getExpirations().stream().map(this::mapToDTO).toList())
                .build();
    }

    private Payment mapToEntity(PaymentRequestDto paymentRequestDTO) {
        return Payment.builder()
                .id(paymentRequestDTO.getId())
                .date(paymentRequestDTO.getDate())
                .amount(paymentRequestDTO.getAmount())
                .supplier(Supplier.builder()
                        .id(paymentRequestDTO.getSupplierId())
                        .build())
                .user_id(paymentRequestDTO.getUserId())
                .expirations(paymentRequestDTO.getExpirations().stream()
                        .map(this::mapToEntity).toList())
                .build();
    }

    private ExpirationPayments mapToEntity(ExpirationPaymentRequestDto expirationPaymentDto) {
        return ExpirationPayments.builder()
                .paymentId(expirationPaymentDto.getPaymentId())
                .paymentPart(expirationPaymentDto.getPaymentPart())
                .expirationId(expirationPaymentDto.getExpirationId())
                .expirationPart(expirationPaymentDto.getExpirationPart())
                .id(expirationPaymentDto.getId())
                .build();
    }

    private ExpirationPaymentResponseDto mapToDTO(ExpirationPayments expirationPayments) {
        return ExpirationPaymentResponseDto.builder()
                .paymentId(expirationPayments.getPaymentId())
                .paymentPart(expirationPayments.getPaymentPart())
                .expirationId(expirationPayments.getExpirationId())
                .expirationPart(expirationPayments.getExpirationPart())
                .id(expirationPayments.getId())
                .build();
    }

}
