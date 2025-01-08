package com.henry.expenseTracker.service;

import com.henry.expenseTracker.Dto.request.ExpirationPaymentRequestDto;
import com.henry.expenseTracker.Dto.request.PaymentRequestDto;
import com.henry.expenseTracker.Dto.response.PaymentResponseDto;
import com.henry.expenseTracker.entity.jpa.ExpirationPayments;
import com.henry.expenseTracker.entity.jpa.Payment;
import com.henry.expenseTracker.entity.jpa.Supplier;
import com.henry.expenseTracker.repository.jpa.ExpirationPaymentsRepository;
import com.henry.expenseTracker.repository.jpa.PaymentRepository;
import com.henry.expenseTracker.service.impl.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class PaymentsServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private ExpirationPaymentsRepository expirationPaymentsRepository;

    @InjectMocks
    private PaymentService paymentService;

    private Payment samplePayment;
    private List<ExpirationPayments> sampleExpirationPayments;

    @BeforeEach
    void setUp(){
        sampleExpirationPayments = new ArrayList<>();
        sampleExpirationPayments.add(
                ExpirationPayments.builder()
                    .paymentPart(.5)
                    .expirationPart(1.)
                    .expirationId(1L)
                    .build()
        );
        sampleExpirationPayments.add(
                ExpirationPayments.builder()
                    .paymentPart(.5)
                    .expirationPart(1.)
                    .expirationId(2L)
                    .build()
        );

        samplePayment = Payment.builder()
                .id(1L)
                .date(LocalDate.now())
                .supplier(Supplier.builder().id(1L).build())
                .user_id(1L)
                .amount(100.5)
                .expirations(new ArrayList<>())
                .build();
    }

    @DisplayName("Save Payment service")
    @Test
    void testSavePayment() throws Exception {

        when(paymentRepository.save(any(Payment.class))).thenReturn(samplePayment);
        paymentService.save(mapToRequestDTO(samplePayment));
        //then
        verify(paymentRepository,times(1)).save(any(Payment.class));
    }

    @DisplayName("List all Payment Service")
    @Test
    void testListAllPayment(){
        Payment payment2 = Payment.builder()
                .id(2L)
                .date(LocalDate.now())
                .supplier(Supplier.builder().id(1L).build())
                .user_id(1L)
                .amount(100.5)
                .expirations(new ArrayList<>())
                .build();
        given(paymentRepository.findAll())
                .willReturn(List.of(samplePayment,payment2));

        List<PaymentResponseDto> foundList = paymentService.findAll();

        assertThat(foundList).isNotNull();
        assertThat(foundList.size()).isEqualTo(2);
    }

    private PaymentRequestDto mapToRequestDTO(Payment payment) {
        return PaymentRequestDto.builder()
                .id(payment.getId())
                .date(payment.getDate())
                .amount(payment.getAmount())
                .userId(payment.getUser_id())
                .supplierId(payment.getSupplier().getId())
                .expirations(payment.getExpirations().stream().map(this::mapToRequestDTO).toList())
                .build();
    }
    private ExpirationPaymentRequestDto mapToRequestDTO(ExpirationPayments expirationPayments) {
        return ExpirationPaymentRequestDto.builder()
                .paymentId(expirationPayments.getPaymentId())
                .paymentPart(expirationPayments.getPaymentPart())
                .expirationId(expirationPayments.getExpirationId())
                .expirationPart(expirationPayments.getExpirationPart())
                .id(expirationPayments.getId())
                .build();
    }

}
