package com.henry.expenseTracker.controller.api.impl;

import com.henry.expenseTracker.controller.api.IController;
import com.henry.expenseTracker.entity.Payment;
import com.henry.expenseTracker.service.impl.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment")
public class PaymentController implements IController<Payment> {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public ResponseEntity<List<Payment>> findAll(@RequestHeader Integer userId) {
        ResponseEntity<List<Payment>> response = null;
        if (userId.describeConstable().isPresent()) {
            response = ResponseEntity.status(HttpStatus.OK).body(paymentService.findAll());
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> findByPk(@PathVariable Integer id) {
        Payment payment = paymentService.findByPk(id).orElse(null);
        return ResponseEntity.status(HttpStatus.OK).body(payment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        ResponseEntity<String> response = null;
        if (paymentService.findByPk(id).isPresent()) {
            paymentService.delete(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @PutMapping
    public ResponseEntity<Payment> update(Payment payment) {
        return ResponseEntity.ok(paymentService.update(payment));
    }

    @PostMapping
    public ResponseEntity<Payment> save(Payment payment) {
        return ResponseEntity.ok(paymentService.save(payment));
    }
}
