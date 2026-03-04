package com.yourcompany.paymentgateway.dto;

import jakarta.validation.constraints.NotBlank;

public class CustomerLoginRequest {

    @NotBlank(message = "email is required")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
