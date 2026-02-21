package com.yourcompany.paymentgateway.dto;

import jakarta.validation.constraints.NotBlank;

public class CompletePaymentRequest {

    @NotBlank(message = "transactionNumber is required")
    private String transactionNumber;

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }
}
