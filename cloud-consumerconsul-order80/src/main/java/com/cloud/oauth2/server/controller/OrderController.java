package com.cloud.oauth2.server.controller;

import com.cloud.api.common.domain.CommonResult;
import com.cloud.api.common.domain.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Value("${payment-service.url}")
    private String paymentServiceUrl;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public CommonResult save(@RequestBody Payment payment) {
        HttpEntity httpEntity = new HttpEntity(payment);
        ResponseEntity<CommonResult> exchange = this.restTemplate.exchange(paymentServiceUrl, HttpMethod.POST, httpEntity, CommonResult.class);
        log.info("save payment {} and return result {}", payment, exchange);
        return exchange.getBody();
    }

    @GetMapping("/{id}")
    public CommonResult save(@PathVariable Long id) {
        ResponseEntity<CommonResult> exchange = this.restTemplate.getForEntity(paymentServiceUrl + "/" + id, CommonResult.class);
        log.info("get payment by id {} and return result {}", id, exchange);
        return exchange.getBody();
    }

    @GetMapping("/say/{what}")
    public CommonResult test(@PathVariable String what) {
        return new CommonResult(200, what);
    }


}
