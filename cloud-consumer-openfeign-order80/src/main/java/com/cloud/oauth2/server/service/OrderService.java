package com.cloud.oauth2.server.service;

import com.cloud.api.common.domain.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "cloud-payment-service")
public interface OrderService {

    @GetMapping("/payment/{id}")
    CommonResult getById(@PathVariable("id") Long id);
}
