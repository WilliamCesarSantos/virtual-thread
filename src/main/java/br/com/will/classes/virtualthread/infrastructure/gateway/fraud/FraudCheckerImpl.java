package br.com.will.classes.virtualthread.infrastructure.gateway.fraud;

import br.com.will.classes.virtualthread.port.FraudChecker;
import org.springframework.stereotype.Component;

@Component
public class FraudCheckerImpl implements FraudChecker {

    private final FraudFeignClient client;

    public FraudCheckerImpl(FraudFeignClient client) {
        this.client = client;
    }

    @Override
    public boolean isFraud(String orderId, double amount) {
        return Boolean.TRUE.equals(
                client.check(orderId, amount)
        );
    }

}