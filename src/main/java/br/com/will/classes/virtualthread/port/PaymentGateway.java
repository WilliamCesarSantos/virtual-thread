package br.com.will.classes.virtualthread.port;

public interface PaymentGateway {

    String charge(String orderId, double amount);

}