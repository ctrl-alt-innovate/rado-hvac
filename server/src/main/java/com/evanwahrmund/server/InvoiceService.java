package com.evanwahrmund.server;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final MailService mailService;

    public InvoiceService(InvoiceRepository invoiceRepository, MailService mailService) {
        this.invoiceRepository = invoiceRepository;
        this.mailService = mailService;
    }

    public Invoice saveInvoice(Invoice invoice) {

        Invoice saved = invoiceRepository.save(invoice);

        String subject = "Invoice for Order at Rado HVAC";

        StringBuilder body = new StringBuilder();
        body.append("Hi ").append(invoice.getName()).append(", thanks for your order!\n\n");
        body.append("Order total: $").append(String.format("%.2f", invoice.getTotal())).append("\n\n");
        body.append("Services:\n");

        for (com.evanwahrmund.server.Service service : invoice.getServices()) {
            body.append("- ").append(service.getName())
                    .append(": $")
                    .append(String.format("%.2f", service.getPrice()))
                    .append("\n");
        }
        mailService.sendConfirmation(invoice.getEmail(), subject, body.toString());

        StringBuilder bodyReturn = new StringBuilder();
        bodyReturn.append("New Order from ").append(invoice.getName()).append(". \n");
        bodyReturn.append("Email: ").append(invoice.getEmail()).append("\n");

        bodyReturn.append("Order total: $").append(String.format("%.2f", invoice.getTotal())).append("\n\n");
        bodyReturn.append("Services:\n");

        for (com.evanwahrmund.server.Service service : invoice.getServices()) {
            bodyReturn.append("- ").append(service.getName())
                    .append(": $")
                    .append(String.format("%.2f", service.getPrice()))
                    .append("\n");

        }
        mailService.sendConfirmation("ewahrmund1@gmail.com",
                "New Invoice",
                bodyReturn.toString());


        return saved;


    }
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }
}
