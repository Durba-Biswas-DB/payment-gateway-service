package com.yourcompany.paymentgateway.service;

import com.yourcompany.paymentgateway.dto.CompletePaymentRequest;
import com.yourcompany.paymentgateway.dto.CompletePaymentResponse;
import com.yourcompany.paymentgateway.dto.CreatePaymentRequest;
import com.yourcompany.paymentgateway.dto.CreatePaymentResponse;
import com.yourcompany.paymentgateway.dto.InvoiceResponse;
import com.yourcompany.paymentgateway.entity.Payment;
import com.yourcompany.paymentgateway.entity.PaymentStatus;
import com.yourcompany.paymentgateway.exception.ResourceNotFoundException;
import com.yourcompany.paymentgateway.repository.PaymentRepository;
import com.yourcompany.paymentgateway.util.QrCodeGenerator;
import com.yourcompany.paymentgateway.util.TransactionNumberGenerator;
import com.yourcompany.paymentgateway.util.InvoicePdfGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final TransactionNumberGenerator transactionNumberGenerator;
    private final QrCodeGenerator qrCodeGenerator;
    private final InvoicePdfGenerator invoicePdfGenerator;


    public PaymentServiceImpl(PaymentRepository paymentRepository,
                          TransactionNumberGenerator transactionNumberGenerator,
                          QrCodeGenerator qrCodeGenerator,
                          InvoicePdfGenerator invoicePdfGenerator) {
        this.paymentRepository = paymentRepository;
        this.transactionNumberGenerator = transactionNumberGenerator;
        this.qrCodeGenerator = qrCodeGenerator;
        this.invoicePdfGenerator = invoicePdfGenerator;
    }


    @Override
    public CreatePaymentResponse createPayment(CreatePaymentRequest request) {
        Payment payment = new Payment();
        payment.setTransactionNumber(transactionNumberGenerator.generate());
        payment.setCustomerId(request.getCustomerId());
        payment.setAmount(request.getAmount());
        payment.setStatus(PaymentStatus.CREATED);

        String qrPayload = "txn=" + payment.getTransactionNumber()
                + "&customerId=" + payment.getCustomerId()
                + "&amount=" + payment.getAmount();
        payment.setQrData(qrPayload);

        Payment saved = paymentRepository.save(payment);

        String qrBase64 = qrCodeGenerator.generateBase64Png(saved.getQrData(), 250, 250);

        CreatePaymentResponse response = new CreatePaymentResponse();
        response.setTransactionNumber(saved.getTransactionNumber());
        response.setCustomerId(saved.getCustomerId());
        response.setAmount(saved.getAmount());
        response.setStatus(saved.getStatus().name());
        response.setQrCodeBase64(qrBase64);
        response.setCreatedAt(saved.getCreatedAt());
        return response;
    }

    @Override
public CompletePaymentResponse completePayment(CompletePaymentRequest request) {
    Payment payment = paymentRepository.findByTransactionNumber(request.getTransactionNumber())
            .orElseThrow(() -> new ResourceNotFoundException(
                    "Payment not found for transactionNumber: " + request.getTransactionNumber()));

    if (payment.getStatus() == PaymentStatus.SUCCESS) {
        CompletePaymentResponse response = new CompletePaymentResponse();
        response.setTransactionNumber(payment.getTransactionNumber());
        response.setStatus(payment.getStatus().name());
        response.setMessage("Payment is already completed");
        response.setUpdatedAt(payment.getCompletedAt());
        return response;
    }

    payment.setCompletedAt(java.time.LocalDateTime.now());
    payment.setStatus(PaymentStatus.SUCCESS);
    Payment updated = paymentRepository.save(payment);

    CompletePaymentResponse response = new CompletePaymentResponse();
    response.setTransactionNumber(updated.getTransactionNumber());
    response.setStatus(updated.getStatus().name());
    response.setMessage("Payment completed successfully");
    response.setUpdatedAt(updated.getCompletedAt());
    return response;
}


    @Override
    @Transactional(readOnly = true)
    public InvoiceResponse generateInvoice(String transactionNumber) {
        Payment payment = paymentRepository.findByTransactionNumber(transactionNumber)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Payment not found for transactionNumber: " + transactionNumber));

        if (payment.getInvoiceReference() == null || payment.getInvoiceReference().isBlank()) {
            payment.setInvoiceReference("INV-" + payment.getTransactionNumber());
            paymentRepository.save(payment);
        }

        InvoiceResponse response = new InvoiceResponse();
        response.setTransactionNumber(payment.getTransactionNumber());
        response.setCustomerId(payment.getCustomerId());
        response.setAmount(payment.getAmount());
        response.setStatus(payment.getStatus().name());
        response.setDate(payment.getUpdatedAt());
        response.setInvoiceReference(payment.getInvoiceReference());
        return response;
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] generateInvoicePdf(String transactionNumber) {
        Payment payment = paymentRepository.findByTransactionNumber(transactionNumber)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Payment not found for transactionNumber: " + transactionNumber));

        if (payment.getInvoiceReference() == null || payment.getInvoiceReference().isBlank()) {
            payment.setInvoiceReference("INV-" + payment.getTransactionNumber());
            paymentRepository.save(payment);
        }

        return invoicePdfGenerator.generate(payment);
    }

}
