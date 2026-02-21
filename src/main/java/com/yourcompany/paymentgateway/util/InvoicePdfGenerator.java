package com.yourcompany.paymentgateway.util;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import com.yourcompany.paymentgateway.entity.Payment;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;

@Component
public class InvoicePdfGenerator {

    public byte[] generate(Payment payment) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        PdfWriter writer = new PdfWriter(outputStream);
        com.itextpdf.kernel.pdf.PdfDocument pdfDocument = new com.itextpdf.kernel.pdf.PdfDocument(writer);
        Document document = new Document(pdfDocument);

        document.add(new Paragraph("Payment Invoice")
                .setBold()
                .setFontSize(18)
                .setTextAlignment(TextAlignment.CENTER));

        document.add(new Paragraph(" "));
        document.add(new Paragraph("Invoice Reference: " + payment.getInvoiceReference()));
        document.add(new Paragraph("Transaction Number: " + payment.getTransactionNumber()));
        document.add(new Paragraph("Customer ID: " + payment.getCustomerId()));
        document.add(new Paragraph("Amount: " + payment.getAmount()));
        document.add(new Paragraph("Status: " + payment.getStatus().name()));
        document.add(new Paragraph("Created At: " + payment.getCreatedAt()));
        document.add(new Paragraph("Completed At: " + payment.getCompletedAt()));

        document.close();
        return outputStream.toByteArray();
    }
}
