package br.com.will.classes.virtualthread.infrastructure.gateway.payment;

import java.math.BigDecimal;

record PaymentRequest(String orderId, BigDecimal amount) {
}