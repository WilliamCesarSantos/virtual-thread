package br.com.will.classes.virtualthread.port;

import java.math.BigDecimal;

public interface PaymentGateway {

    String charge(String orderId, BigDecimal amount);

}