package com.ben.Payment.Service.repo;

import com.ben.Payment.Service.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepo extends JpaRepository<Payment, Long> {

    Payment findPaymentsByPaymentLinkId(String paymentLinkId);

    Long findPaymentsByStoreId(Long storeId);
}
