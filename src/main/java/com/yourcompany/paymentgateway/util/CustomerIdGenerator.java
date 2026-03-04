package com.yourcompany.paymentgateway.util;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CustomerIdGenerator {

    public String generate() {
        String random = UUID.randomUUID().toString().replace("-", "").substring(0, 6).toUpperCase();
        return "CUST-" + random;
    }
}
