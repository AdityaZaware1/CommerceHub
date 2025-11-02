package com.ben.Payment.Service.entity;

import com.ben.Payment.Service.enums.PaymentOrderStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long amount;

    private PaymentOrderStatus status = PaymentOrderStatus.PENDING;

    private Long userId;
    private String paymentLinkId;
    private String userName;
    private Long orderId;
    private Long storeId;
}
