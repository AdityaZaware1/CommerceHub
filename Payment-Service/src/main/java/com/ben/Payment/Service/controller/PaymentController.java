package com.ben.Payment.Service.controller;

import com.ben.Payment.Service.dto.PaymentDto;
import com.ben.Payment.Service.entity.Payment;
import com.ben.Payment.Service.request.PaymentRequest;
import com.ben.Payment.Service.response.PaymentResponse;
import com.ben.Payment.Service.service.PaymentService;
import com.razorpay.RazorpayException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/create/")
    public ResponseEntity<PaymentResponse> createPayment(@RequestBody PaymentDto paymentDto) throws RazorpayException {

        PaymentRequest paymentRequest = new PaymentRequest(paymentDto.getAmount());
        return ResponseEntity.ok(paymentService.createSubScriptionPayment(paymentRequest, paymentDto.getUserId(), paymentDto.getOrderId()));
    }


    @PostMapping("/make")
    public ResponseEntity<PaymentResponse> createRandomPayment(
            @RequestBody Payment paymentRequest) {
        return ResponseEntity.ok(paymentService.createRandomPayment(paymentRequest));
    }

    @GetMapping("/confirmOrder/{stringUrl}")
    public ResponseEntity<Boolean> confirmSubScription(@PathVariable String stringUrl) throws RazorpayException {
        return ResponseEntity.ok(paymentService.updateStorePaymentStatus(stringUrl));
    }


}
