package br.com.will.classes.virtualthread.port;

import br.com.will.classes.virtualthread.domain.Payment;

public interface PaymentRepository {

    boolean existsByOrderId(String orderId);

    void save(Payment payment);

}