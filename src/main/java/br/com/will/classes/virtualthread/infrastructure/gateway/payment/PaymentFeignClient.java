package br.com.will.classes.virtualthread.infrastructure.gateway.payment;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment-gateway", url = "http://wiremock:8080")
interface PaymentFeignClient {

    @PostMapping("/pay")
    String pay(
            @RequestBody PaymentRequest request
    );

}