package com.ben.Payment.Service.service.impl;

import com.ben.Payment.Service.dto.UserDto;
import com.ben.Payment.Service.entity.Payment;
import com.ben.Payment.Service.enums.PaymentOrderStatus;
import com.ben.Payment.Service.external.UserService;
import com.ben.Payment.Service.kafka.KafkaProducerStoreSubScribe;
import com.ben.Payment.Service.kafka.OrderConfirmProducer;
import com.ben.Payment.Service.repo.PaymentRepo;
import com.ben.Payment.Service.request.PaymentRequest;
import com.ben.Payment.Service.response.PaymentResponse;
import com.ben.Payment.Service.service.PaymentService;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepo paymentRepo;
    private final UserService userService;
    private final OrderConfirmProducer orderConfirmProducer;
    private final KafkaProducerStoreSubScribe kafkaProducerStoreSubScribe;

    @Value("${razorpay.api.key}")
    private String apiKey;

    @Value("${razorpay.api.secret}")
    private String apiSecret;

    @Override
    public PaymentResponse createOrderPayment(PaymentRequest paymentRequest, Long userId, Long orderId) {

        UserDto userDto = getUserById(userId);

        if (userDto == null) {
            throw new RuntimeException("User not found");
        }

        Payment payment = new Payment();
        payment.setUserId(userDto.getId());
        payment.setAmount(paymentRequest.getAmount());
        payment.setUserName(userDto.getName());
        payment.setOrderId(orderId);
        payment.setStatus(PaymentOrderStatus.PENDING);

        PaymentResponse paymentResponse = createRandomPayment(payment);
        payment.setPaymentLinkId(paymentResponse.getGetPaymentLinkId());

        paymentRepo.save(payment);

        return paymentResponse;
    }

    @Override
    public PaymentResponse createSubScriptionPayment(PaymentRequest paymentRequest, Long userId, Long storeId) {

        UserDto userDto = getUserById(userId);

        if (userDto == null) {
            throw new RuntimeException("User not found");
        }

        Payment payment = new Payment();
        payment.setUserId(userDto.getId());
        payment.setAmount(paymentRequest.getAmount());
        payment.setUserName(userDto.getName());
        payment.setStoreId(storeId);
        payment.setStatus(PaymentOrderStatus.PENDING);

        PaymentResponse paymentResponse = createRandomPayment(payment);
        payment.setPaymentLinkId(paymentResponse.getGetPaymentLinkId());

        paymentRepo.save(payment);

        return paymentResponse;
    }

    @Override
    public PaymentResponse createRandomPayment(Payment payment) {
        try {
            RazorpayClient razorpayClient = new RazorpayClient(apiKey, apiSecret);

            Long amount = payment.getAmount() * 100;

            JSONObject paymentLinkRequest = new JSONObject();
            paymentLinkRequest.put("amount", amount);
            paymentLinkRequest.put("currency", "INR");
            paymentLinkRequest.put("accept_partial", false);
            paymentLinkRequest.put("description", "Payment for Service");

            JSONObject customer = new JSONObject();

            String userName = payment.getUserName();
            String email = userService.getUserById(payment.getUserId()).getEmail();
            customer.put("name", userName);
            customer.put("email", email);

            paymentLinkRequest.put("customer", customer);

            JSONObject notify = new JSONObject();
            notify.put("email", true);
            notify.put("sms", true);
            paymentLinkRequest.put("notify", notify);

            paymentLinkRequest.put("callback_url", "http://localhost:8085/api/payment?order_id=" + payment.getUserId());
            paymentLinkRequest.put("callback_method", "get");

            PaymentLink paymentLink = razorpayClient.paymentLink.create(paymentLinkRequest);

            // ✅ Log and return payment link
            System.out.println("Payment Link Created: " + paymentLink.toString());

            String shortUrl = paymentLink.get("short_url");
            String paymentId = paymentLink.get("id");

            PaymentResponse paymentResponse = new PaymentResponse();
            paymentResponse.setPaymentUrl(shortUrl);
            paymentResponse.setGetPaymentLinkId(paymentId);

            return paymentResponse;
        }

        catch (RazorpayException e) {
            System.err.println("Error creating Razorpay Payment Link: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Payment link creation failed: " + e.getMessage());
        }
    }

    @Override
    public Payment getPaymentById(Long paymentId) throws Exception {
        return paymentRepo.findById(paymentId)
                .orElseThrow(() -> new Exception("Payment order not found"));
    }

    @Override
    public Boolean updateStorePaymentStatus(String paymentLinkId) throws RazorpayException {
        try {

            RazorpayClient razorpayClient = new RazorpayClient(apiKey, apiSecret);
            PaymentLink paymentLink = razorpayClient.paymentLink.fetch(paymentLinkId);
            String status = paymentLink.get("status");

            Payment payment = paymentRepo.findPaymentsByPaymentLinkId(paymentLinkId);

            PaymentOrderStatus paymentOrderStatus;
            switch (status.toLowerCase()) {
                case "paid":
                    paymentOrderStatus = PaymentOrderStatus.PAID;
                    break;
                case "cancelled":
                    paymentOrderStatus = PaymentOrderStatus.CANCELLED;
                    break;
                case "expired":
                    paymentOrderStatus = PaymentOrderStatus.EXPIRED;
                    break;
                default:
                    paymentOrderStatus = PaymentOrderStatus.PENDING;
                    break;
            }

            payment.setStatus(paymentOrderStatus);

            if(payment.getStatus().equals(PaymentOrderStatus.PAID)) {
                kafkaProducerStoreSubScribe.sendMessage(payment.getStoreId().toString());
            }
            return true;
        }
        catch (Exception e) {
            System.err.println("Error creating Razorpay Payment Link: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean updateOrderPaymentStatus(String paymentLinkId) throws RazorpayException {
        try {

            RazorpayClient razorpayClient = new RazorpayClient(apiKey, apiSecret);
            PaymentLink paymentLink = razorpayClient.paymentLink.fetch(paymentLinkId);
            String status = paymentLink.get("status");

            Payment payment = paymentRepo.findPaymentsByPaymentLinkId(paymentLinkId);

            PaymentOrderStatus paymentOrderStatus;
            switch (status.toLowerCase()) {
                case "paid":
                    paymentOrderStatus = PaymentOrderStatus.PAID;
                    break;
                case "cancelled":
                    paymentOrderStatus = PaymentOrderStatus.CANCELLED;
                    break;
                case "expired":
                    paymentOrderStatus = PaymentOrderStatus.EXPIRED;
                    break;
                default:
                    paymentOrderStatus = PaymentOrderStatus.PENDING;
                    break;
            }

            payment.setStatus(paymentOrderStatus);

            if(payment.getStatus().equals(PaymentOrderStatus.PAID)) {
                orderConfirmProducer.sendMessage(payment.getOrderId().toString());
            }
            return true;
        }
        catch (Exception e) {
            System.err.println("Error creating Razorpay Payment Link: " + e.getMessage());
            return false;
        }
    }

    @CircuitBreaker(name = "userService", fallbackMethod = "UserFallBacks")
    public UserDto getUserById(Long userId) {

        UserDto userDto = userService.getUserById(userId);

        if(userDto.getId() == 0L) {
            throw new RuntimeException("User Service is not available");
        }

        return userDto;
    }

    public UserDto UserFallBacks(Long userId) {
        return new UserDto(0L, "unavilable", null, null, null);
    }
}
