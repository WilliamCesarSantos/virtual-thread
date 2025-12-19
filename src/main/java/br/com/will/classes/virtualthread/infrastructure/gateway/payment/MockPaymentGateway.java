package br.com.will.classes.virtualthread.infrastructure.gateway.payment;

import br.com.will.classes.virtualthread.port.PaymentGateway;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class MockPaymentGateway implements PaymentGateway {

    @Override
    public String charge(String orderId, BigDecimal amount) {
        try {
            TimeUnit.MILLISECONDS.sleep(300);// Network latency simulation
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return UUID.randomUUID().toString();
    }

}