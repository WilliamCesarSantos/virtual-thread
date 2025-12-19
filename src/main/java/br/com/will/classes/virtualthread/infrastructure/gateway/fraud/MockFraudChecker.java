package br.com.will.classes.virtualthread.infrastructure.gateway.fraud;

import br.com.will.classes.virtualthread.port.FraudChecker;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@Component
public class MockFraudChecker implements FraudChecker {

    @Override
    public boolean isFraud(
            String orderId,
            BigDecimal amount
    ) {
        try {
            TimeUnit.MILLISECONDS.sleep(300);// Network latency simulation
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

}