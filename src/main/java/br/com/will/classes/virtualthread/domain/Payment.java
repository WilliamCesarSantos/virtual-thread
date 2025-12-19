package br.com.will.classes.virtualthread.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Payment(
        String orderId,
        BigDecimal amount,
        PaymentStatus status,
        String transactionId,
        LocalDateTime processedAt
) {
}