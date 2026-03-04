package com.yourcompany.paymentgateway.service;

import com.yourcompany.paymentgateway.dto.CustomerLoginRequest;
import com.yourcompany.paymentgateway.dto.CustomerResponse;
import com.yourcompany.paymentgateway.dto.CustomerSignupRequest;
import com.yourcompany.paymentgateway.entity.Customer;
import com.yourcompany.paymentgateway.exception.ResourceNotFoundException;
import com.yourcompany.paymentgateway.repository.CustomerRepository;
import com.yourcompany.paymentgateway.util.CustomerIdGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerIdGenerator customerIdGenerator;

    public CustomerServiceImpl(CustomerRepository customerRepository,
                               CustomerIdGenerator customerIdGenerator) {
        this.customerRepository = customerRepository;
        this.customerIdGenerator = customerIdGenerator;
    }

    @Override
    public CustomerResponse signup(CustomerSignupRequest request) {
        customerRepository.findByEmail(request.getEmail()).ifPresent(c -> {
            throw new IllegalArgumentException("Email already registered");
        });

        customerRepository.findByPhone(request.getPhone()).ifPresent(c -> {
            throw new IllegalArgumentException("Phone already registered");
        });

        Customer customer = new Customer();
        customer.setCustomerId(customerIdGenerator.generate());
        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());

        Customer saved = customerRepository.save(customer);
        return toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerResponse login(CustomerLoginRequest request) {
        Customer customer = customerRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found for email: " + request.getEmail()));
        return toResponse(customer);
    }

    private CustomerResponse toResponse(Customer customer) {
        CustomerResponse response = new CustomerResponse();
        response.setCustomerId(customer.getCustomerId());
        response.setName(customer.getName());
        response.setEmail(customer.getEmail());
        response.setPhone(customer.getPhone());
        return response;
    }
}
