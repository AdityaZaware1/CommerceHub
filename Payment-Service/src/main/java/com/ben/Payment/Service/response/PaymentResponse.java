package com.ben.Payment.Service.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponse {

    private String paymentUrl;
    private String getPaymentLinkId;
}
