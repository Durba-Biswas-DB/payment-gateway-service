package com.yourcompany.paymentgateway.service;

import com.yourcompany.paymentgateway.dto.CompletePaymentRequest;
import com.yourcompany.paymentgateway.dto.CompletePaymentResponse;
import com.yourcompany.paymentgateway.dto.CreatePaymentRequest;
import com.yourcompany.paymentgateway.dto.CreatePaymentResponse;
import com.yourcompany.paymentgateway.dto.InvoiceResponse;

public interface PaymentService {

    CreatePaymentResponse createPayment(CreatePaymentRequest request);

    CompletePaymentResponse completePayment(CompletePaymentRequest request);

    InvoiceResponse generateInvoice(String transactionNumber);

    byte[] generateInvoicePdf(String transactionNumber);

}
