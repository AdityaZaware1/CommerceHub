package com.ben.Order.Service.external;

import com.ben.Order.Service.model.dto.PaymentDto;
import com.ben.Order.Service.model.response.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment-service", url = "${payment.service.url}")
public interface OrderPaymentService {

    @PostMapping("/createPayment")
    public PaymentResponse createPayment(@RequestBody PaymentDto paymentDto);
}
