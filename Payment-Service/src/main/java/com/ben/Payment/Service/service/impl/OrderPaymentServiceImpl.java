package com.ben.Payment.Service.service.impl;

import com.ben.Payment.Service.request.PaymentRequest;
import com.ben.Payment.Service.response.PaymentResponse;
import com.ben.Payment.Service.service.PaymentService;
import com.ben.Payment.Service.service.OrderPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderPaymentServiceImpl implements OrderPaymentService {

    private final PaymentService paymentService;

    @Override
    public PaymentResponse payOrder(Long amount, Long userId, Long orderId) {

        PaymentRequest paymentRequest = new PaymentRequest(amount);

        PaymentResponse paymentResponse = paymentService.createOrderPayment(paymentRequest, userId, orderId);

        return paymentResponse;
    }
    
}
