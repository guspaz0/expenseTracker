package com.henry.expenseTracker.controller.api;

import com.henry.expenseTracker.Dto.request.PaymentRequestDto;
import com.henry.expenseTracker.Dto.response.PaymentResponseDto;
import com.henry.expenseTracker.exceptions.ErrorsResponse;
import com.henry.expenseTracker.service.impl.PaymentService;
import com.henry.expenseTracker.service.impl.UserService;
import com.henry.expenseTracker.util.annotations.Notify;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="Payments")
@RestController
@RequestMapping("/api/payment")
@AllArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    private final UserService userService;

    @Operation(summary="List all payments")
    @SneakyThrows
    @GetMapping
    public ResponseEntity<List<PaymentResponseDto>> findAll(@RequestHeader Long userId) {
        userService.findById(userId);
        return ResponseEntity.ok(paymentService.findAll());
    }

    @Operation(summary="Get payment identified by id")
    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.findById(id));
    }

    @Operation(summary="Delete payment by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        paymentService.delete(id);
        return ResponseEntity.ok("Payment id: "+id+" deleted");
    }

    @Operation(summary="Update payment")
    @PutMapping
    public ResponseEntity<PaymentResponseDto> update(@Valid @RequestBody PaymentRequestDto payment) {
        return ResponseEntity.ok(paymentService.update(payment));
    }

    @Operation(summary="Create payment")
    @ApiResponse(
            responseCode = "400",
            description = "when a field is missing or invalid we reponse this",
            content = {
                    @Content(mediaType = "application/json", schema=@Schema(implementation = ErrorsResponse.class))
            }
    )
    @SneakyThrows
    @PostMapping
    @Notify(value="Payment Notice")
    public ResponseEntity<PaymentResponseDto> save(@Valid @RequestBody PaymentRequestDto paymentRequest) {
        return ResponseEntity.ok(paymentService.save(paymentRequest));
    }

}
