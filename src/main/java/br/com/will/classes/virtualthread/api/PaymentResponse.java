package br.com.will.classes.virtualthread.api;

import br.com.will.classes.virtualthread.domain.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentResponse(
        String orderId,
        PaymentStatus status,
        LocalDateTime processedAt,
        BigDecimal amount
) {
}

