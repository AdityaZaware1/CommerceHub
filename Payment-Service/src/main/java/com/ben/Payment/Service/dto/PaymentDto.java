package com.ben.Payment.Service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto{

    private Long amount;
    private Long userId;
    private Long orderId;
}
