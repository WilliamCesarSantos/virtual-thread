package br.com.will.classes.virtualthread.api;

import br.com.will.classes.virtualthread.domain.Payment;
import br.com.will.classes.virtualthread.usecase.ProcessPaymentUseCase;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final ProcessPaymentUseCase useCase;

    public PaymentController(ProcessPaymentUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public Payment pay(
            @RequestParam @NotBlank String orderId,
            @RequestParam @Positive BigDecimal amount
    ) {
        return useCase.execute(orderId, amount);
    }
}