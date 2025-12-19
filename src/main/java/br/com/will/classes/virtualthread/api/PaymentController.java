package br.com.will.classes.virtualthread.api;

import br.com.will.classes.virtualthread.usecase.ProcessPaymentUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final ProcessPaymentUseCase useCase;

    public PaymentController(ProcessPaymentUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<PaymentResponse> processPayment(
            @Valid @RequestBody PaymentRequest request
    ) {
        var payment = useCase.execute(request.getOrderId(), request.getAmount());
        return ResponseEntity.ok(
                new PaymentResponse(
                        payment.orderId(),
                        payment.status(),
                        payment.processedAt(),
                        payment.amount()
                )
        );
    }

}