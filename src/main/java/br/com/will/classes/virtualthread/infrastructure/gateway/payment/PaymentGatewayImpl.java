package br.com.will.classes.virtualthread.infrastructure.gateway.payment;

import br.com.will.classes.virtualthread.port.PaymentGateway;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PaymentGatewayImpl implements PaymentGateway {

    private final PaymentFeignClient client;

    public PaymentGatewayImpl(PaymentFeignClient client) {
        this.client = client;
    }

    @Override
    public String charge(String orderId, BigDecimal amount) {
        return client.pay(new PaymentRequest(orderId, amount));
    }

}