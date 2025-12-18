package br.com.will.classes.virtualthread.domain;

public record Payment(
        String orderId,
        double amount,
        PaymentStatus status,
        String transactionId
) {
}