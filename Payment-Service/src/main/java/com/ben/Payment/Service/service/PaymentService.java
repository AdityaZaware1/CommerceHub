package com.ben.Payment.Service.service;

import com.ben.Payment.Service.entity.Payment;
import com.ben.Payment.Service.request.PaymentRequest;
import com.ben.Payment.Service.response.PaymentResponse;
import com.razorpay.RazorpayException;

public interface PaymentService {

    PaymentResponse createOrderPayment(PaymentRequest paymentRequest, Long userId, Long orderId);

    PaymentResponse createSubScriptionPayment(PaymentRequest paymentRequest, Long userId, Long storeId);

    PaymentResponse createRandomPayment(Payment paymentRequest);

    Payment getPaymentById(Long paymentId) throws Exception;

    Boolean updateStorePaymentStatus(String paymentLinkId) throws RazorpayException;

    Boolean updateOrderPaymentStatus(String paymentLinkId) throws RazorpayException;
}
