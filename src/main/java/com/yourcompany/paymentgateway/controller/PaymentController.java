package com.yourcompany.paymentgateway.controller;

import com.yourcompany.paymentgateway.dto.CompletePaymentRequest;
import com.yourcompany.paymentgateway.dto.CompletePaymentResponse;
import com.yourcompany.paymentgateway.dto.CreatePaymentRequest;
import com.yourcompany.paymentgateway.dto.CreatePaymentResponse;
import com.yourcompany.paymentgateway.dto.InvoiceResponse;
import com.yourcompany.paymentgateway.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<CreatePaymentResponse> createPayment(@Valid @RequestBody CreatePaymentRequest request) {
        return ResponseEntity.ok(paymentService.createPayment(request));
    }

    @PostMapping("/complete")
    public ResponseEntity<CompletePaymentResponse> completePayment(@Valid @RequestBody CompletePaymentRequest request) {
        return ResponseEntity.ok(paymentService.completePayment(request));
    }

    @GetMapping("/invoice/{transactionNumber}")
    public ResponseEntity<InvoiceResponse> generateInvoice(@PathVariable String transactionNumber) {
        return ResponseEntity.ok(paymentService.generateInvoice(transactionNumber));
    }

    @GetMapping("/invoice/{transactionNumber}/pdf")
    public ResponseEntity<byte[]> downloadInvoicePdf(@PathVariable String transactionNumber) {
        byte[] pdfBytes = paymentService.generateInvoicePdf(transactionNumber);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=invoice-" + transactionNumber + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }

}
