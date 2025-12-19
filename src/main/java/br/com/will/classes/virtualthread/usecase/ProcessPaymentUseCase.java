package br.com.will.classes.virtualthread.usecase;

import br.com.will.classes.virtualthread.domain.Payment;
import br.com.will.classes.virtualthread.domain.PaymentStatus;
import br.com.will.classes.virtualthread.port.FraudChecker;
import br.com.will.classes.virtualthread.port.PaymentGateway;
import br.com.will.classes.virtualthread.port.PaymentRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class ProcessPaymentUseCase {

    private final PaymentRepository repository;
    private final PaymentGateway gateway;
    private final FraudChecker fraudChecker;

    public ProcessPaymentUseCase(PaymentRepository repository,
                                 PaymentGateway gateway,
                                 FraudChecker fraudChecker) {
        this.repository = repository;
        this.gateway = gateway;
        this.fraudChecker = fraudChecker;
    }

    public Payment execute(String orderId, BigDecimal amount) {
        if (repository.existsByOrderId(orderId)) {
            throw new IllegalStateException("Payment already processed");
        }

        if (fraudChecker.isFraud(orderId, amount)) {
            Payment fraud = new Payment(
                    orderId,
                    amount,
                    PaymentStatus.FRAUD,
                    null,
                    LocalDateTime.now()
            );
            repository.save(fraud);
            return fraud;
        }

        String transactionId = gateway.charge(orderId, amount);

        Payment payment = new Payment(
                orderId,
                amount,
                PaymentStatus.APPROVED,
                transactionId,
                LocalDateTime.now()
        );
        repository.save(payment);
        return payment;
    }

}