package br.com.will.classes.virtualthread.infrastructure.gateway.fraud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(name = "fraud-service", url = "${fraud.service.url}")
interface FraudFeignClient {

    @PostMapping("/api/v1/fraud-analysis")
    String check(
            @RequestParam String orderId,
            @RequestParam BigDecimal amount
    );

}