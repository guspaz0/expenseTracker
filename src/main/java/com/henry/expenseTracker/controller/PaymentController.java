package com.henry.expenseTracker.controller;

import com.henry.expenseTracker.Dto.request.PaymentRequestDto;
import com.henry.expenseTracker.Dto.response.PaymentResponseDto;
import com.henry.expenseTracker.service.impl.PaymentService;
import com.henry.expenseTracker.service.impl.UserService;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    private final PaymentService paymentService;
    private final UserService userService;

    public PaymentController(PaymentService paymentService, UserService userService) {
        this.paymentService = paymentService;
        this.userService = userService;
    }

    @SneakyThrows
    @GetMapping
    public ResponseEntity<List<PaymentResponseDto>> findAll(@RequestHeader Long userId) {
        userService.findById(userId);
        return ResponseEntity.ok(paymentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        paymentService.delete(id);
        return ResponseEntity.ok("Payment id: "+id+" deleted");
    }

    @PutMapping
    public ResponseEntity<PaymentResponseDto> update(@RequestBody PaymentRequestDto payment) {
        return ResponseEntity.ok(paymentService.update(payment));
    }

    @SneakyThrows
    @PostMapping
    public ResponseEntity<PaymentResponseDto> save(@RequestBody PaymentRequestDto paymentRequest) {
        return ResponseEntity.ok(paymentService.save(paymentRequest));
    }

}
