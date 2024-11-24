package com.henry.expenseTracker.service.impl;

import com.henry.expenseTracker.dao.IDao;
import com.henry.expenseTracker.dao.impl.PaymentDaoH2;
import com.henry.expenseTracker.entity.Payment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService implements IDao<Payment> {
    private IDao<Payment> paymentDaoGeneric;

    public PaymentService(){
        this.paymentDaoGeneric = new PaymentDaoH2();
    }

    public List<Payment> findAll() {
        return paymentDaoGeneric.findAll();
    }

    public Payment update(Payment payment) {
        return null;
    }

    public Optional<Payment> findByPk(int id) {
        return paymentDaoGeneric.findByPk(id);
    }

    public Payment save(Payment payment) {
        return paymentDaoGeneric.save(payment);
    }

    public void delete(int id) {
        paymentDaoGeneric.delete(id);
    }
}
