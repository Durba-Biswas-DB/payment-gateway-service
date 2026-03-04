package com.yourcompany.paymentgateway.controller;

import com.yourcompany.paymentgateway.dto.CustomerLoginRequest;
import com.yourcompany.paymentgateway.dto.CustomerResponse;
import com.yourcompany.paymentgateway.dto.CustomerSignupRequest;
import com.yourcompany.paymentgateway.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
@CrossOrigin(origins = "http://localhost:5173")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/signup")
    public ResponseEntity<CustomerResponse> signup(@Valid @RequestBody CustomerSignupRequest request) {
        return ResponseEntity.ok(customerService.signup(request));
    }

    @PostMapping("/login")
    public ResponseEntity<CustomerResponse> login(@Valid @RequestBody CustomerLoginRequest request) {
        return ResponseEntity.ok(customerService.login(request));
    }
}
