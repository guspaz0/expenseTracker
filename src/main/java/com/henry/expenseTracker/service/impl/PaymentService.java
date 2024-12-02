package com.henry.expenseTracker.service.impl;

import com.henry.expenseTracker.entity.Payment;
import com.henry.expenseTracker.repository.PaymentRepository;
import com.henry.expenseTracker.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService implements IService<Payment> {
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    @Override
    public Optional<Payment> findById(Long id) {

        return paymentRepository.findById(id);
    }

    @Override
    public Payment save(Payment payment) {

        return paymentRepository.save(payment);
    }

    @Override
    public void update(Payment payment) {
        Optional<Payment> optionalPayment = this.findById(payment.getId());
        if (optionalPayment.isPresent()) {
            paymentRepository.save(payment);
        }
    }
    @Override
    public void delete(Long id) {
        paymentRepository.deleteById(id);
    }
}
