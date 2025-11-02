package com.ben.Store.Service.model.response;

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
