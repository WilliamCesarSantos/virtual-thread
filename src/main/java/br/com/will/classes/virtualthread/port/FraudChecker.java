package br.com.will.classes.virtualthread.port;

import java.math.BigDecimal;

public interface FraudChecker {

    boolean isFraud(String orderId, BigDecimal amount);

}