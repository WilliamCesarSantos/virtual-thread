package br.com.will.classes.virtualthread.domain;

import java.math.BigDecimal;

public record Payment(
        String orderId,
        BigDecimal amount,
        PaymentStatus status,
        String transactionId
) {
}