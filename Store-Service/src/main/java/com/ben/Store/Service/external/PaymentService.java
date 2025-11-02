package com.ben.Store.Service.external;

import com.ben.Store.Service.external.resillence4j.PaymentFallBack;
import com.ben.Store.Service.model.dto.PaymentDto;
import com.ben.Store.Service.model.response.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "Payment-Service",
        url = "${user.service.url}",
        fallback = PaymentFallBack.class)
public interface PaymentService {

    @PostMapping("/create/")
    public PaymentResponse createPayment(@RequestBody PaymentDto paymentDto);
}
