package br.com.will.classes.virtualthread.infrastructure.gateway.fraud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "fraud-service", url = "http://wiremock:8080")
interface FraudFeignClient {

    @PostMapping("/fraud-analysis")
    Boolean check(
            @RequestParam String orderId,
            @RequestParam double amount
    );

}