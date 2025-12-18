package br.com.will.classes.virtualthread.infrastructure.gateway.payment;

record PaymentRequest(String orderId, double amount) {
}