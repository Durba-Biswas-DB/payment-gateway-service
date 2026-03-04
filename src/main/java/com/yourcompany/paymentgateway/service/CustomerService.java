package com.yourcompany.paymentgateway.service;

import com.yourcompany.paymentgateway.dto.CustomerLoginRequest;
import com.yourcompany.paymentgateway.dto.CustomerResponse;
import com.yourcompany.paymentgateway.dto.CustomerSignupRequest;

public interface CustomerService {

    CustomerResponse signup(CustomerSignupRequest request);

    CustomerResponse login(CustomerLoginRequest request);
}
