package br.com.will.classes.virtualthread.infrastructure.gateway.payment;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment-gateway", url = "${payment.service.url}")
interface PaymentFeignClient {

    @PostMapping("/api/v1/pay")
    String pay(
            @RequestBody PaymentRequest request
    );

}