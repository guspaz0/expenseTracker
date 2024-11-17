package service;

import dao.IDao;
import entity.Payment;

import java.util.List;
import java.util.Optional;

public class paymentService implements IDao<Payment> {
    private IDao<Payment> paymentDaoGeneric;

    public paymentService(IDao<Payment> paymentDaoGeneric){
        this.paymentDaoGeneric = paymentDaoGeneric;
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
