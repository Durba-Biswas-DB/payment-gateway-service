package com.yourcompany.paymentgateway.util;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Component
public class TransactionNumberGenerator {

    public String generate() {
        String datePart = LocalDate.now().toString().replace("-", "");
        String randomPart = UUID.randomUUID().toString().replace("-", "").substring(0, 6).toUpperCase();
        return "TXN-" + datePart + "-" + randomPart;
    }
}
