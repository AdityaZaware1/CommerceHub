package com.ben.Payment.Service.service;

import com.ben.Payment.Service.request.PaymentRequest;
import com.ben.Payment.Service.response.PaymentResponse;

public interface OrderPaymentService {

    public PaymentResponse payOrder(Long amonut, Long userId, Long orderId);

    //public Boolean confirmOrder(String paymentLinkId);
}
