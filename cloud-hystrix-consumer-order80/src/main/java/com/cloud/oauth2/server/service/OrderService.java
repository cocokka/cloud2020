package com.cloud.oauth2.server.service;

import com.cloud.oauth2.server.service.impl.OrderServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE", fallbackFactory = OrderServiceFallback.class)
public interface OrderService {

    @GetMapping("/payment/timeout")
    String timeout();

    @GetMapping("/payment/exception")
    String exception();

}
