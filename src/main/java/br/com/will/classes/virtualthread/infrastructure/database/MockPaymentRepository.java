package br.com.will.classes.virtualthread.infrastructure.database;

import br.com.will.classes.virtualthread.domain.Payment;
import br.com.will.classes.virtualthread.port.PaymentRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

@Repository
public class MockPaymentRepository implements PaymentRepository {

    @Override
    public boolean existsByOrderId(String orderId) {
        return false;
    }

    @Override
    public void save(Payment payment) {
        // Mock implementation does nothing
    }

}
