package br.com.will.classes.virtualthread.infrastructure.gateway.fraud;

import br.com.will.classes.virtualthread.port.FraudChecker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class FraudCheckerImpl implements FraudChecker {

    private final FraudFeignClient client;

    public FraudCheckerImpl(FraudFeignClient client) {
        this.client = client;
    }

    @Override
    public boolean isFraud(
            String orderId,
            BigDecimal amount
    ) {
        return client.check(orderId, amount)
                .equalsIgnoreCase("true");
    }

}