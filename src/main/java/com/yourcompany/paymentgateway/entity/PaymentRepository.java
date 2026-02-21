package com.yourcompany.paymentgateway.repository;

import com.yourcompany.paymentgateway.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByTransactionNumber(String transactionNumber);
}
