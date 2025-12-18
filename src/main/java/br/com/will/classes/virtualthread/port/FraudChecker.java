package br.com.will.classes.virtualthread.port;

public interface FraudChecker {

    boolean isFraud(String orderId, double amount);

}