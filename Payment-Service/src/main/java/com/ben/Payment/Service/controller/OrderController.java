package com.ben.Payment.Service.controller;

import com.ben.Payment.Service.dto.PaymentDto;
import com.ben.Payment.Service.request.PaymentRequest;
import com.ben.Payment.Service.response.PaymentResponse;
import com.ben.Payment.Service.service.OrderPaymentService;
import com.ben.Payment.Service.service.PaymentService;
import com.razorpay.RazorpayException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payment/")
public class OrderController {

    private final OrderPaymentService orderPaymentService;
    private final PaymentService paymentService;

    @PostMapping("/createPayment")
    public ResponseEntity<PaymentResponse> createPayment(
             @RequestBody PaymentDto request) {
        return ResponseEntity.ok(orderPaymentService.payOrder(request.getAmount(), request.getUserId(), request.getOrderId()));
    }

    @GetMapping("/confirmOrder/{paymentLinkId}")
    public ResponseEntity<Boolean> confirmOrder(@PathVariable String paymentLinkId) throws RazorpayException {
        return ResponseEntity.ok(paymentService.updateOrderPaymentStatus(paymentLinkId));
    }
}
