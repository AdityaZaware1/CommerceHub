package com.ben.Store.Service.external.resillence4j;

import com.ben.Store.Service.external.PaymentService;
import com.ben.Store.Service.model.dto.PaymentDto;
import com.ben.Store.Service.model.response.PaymentResponse;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class PaymentFallBack implements PaymentService {
    @Override
    public PaymentResponse createPayment(PaymentDto paymentDto) {
        return new PaymentResponse();
    }
}
