package com.henry.expenseTracker.controller.api.impl;

import com.henry.expenseTracker.controller.api.IController;
import com.henry.expenseTracker.entity.Payment;
import com.henry.expenseTracker.entity.User;
import com.henry.expenseTracker.service.impl.PaymentService;
import com.henry.expenseTracker.service.impl.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/payment")
public class PaymentController implements IController<Payment> {
    private final PaymentService paymentService;
    private final UserService userService;

    public PaymentController(PaymentService paymentService, UserService userService) {
        this.paymentService = paymentService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<Payment>> findAll(@RequestHeader Long userId) {
        ResponseEntity<List<Payment>> response = null;
        Optional<User> optionalUser = userService.findById(userId);
        if (optionalUser.isPresent()) {
            response = ResponseEntity.status(HttpStatus.OK).body(paymentService.findAll());
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> findById(@PathVariable Long id) {
        Payment payment = paymentService.findById(id).orElse(null);
        return ResponseEntity.status(HttpStatus.OK).body(payment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        ResponseEntity<String> response = null;
        if (paymentService.findById(id).isPresent()) {
            paymentService.delete(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody Payment payment) {
        try {
            paymentService.update(payment);
            return ResponseEntity.ok("Payment updated Successfully");
        } catch (Exception e) {
            return ResponseEntity.ok("Error updating payment");
        }

    }

    @PostMapping
    public ResponseEntity<Payment> save(@RequestBody Payment payment) {
        return ResponseEntity.ok(paymentService.save(payment));
    }
}
