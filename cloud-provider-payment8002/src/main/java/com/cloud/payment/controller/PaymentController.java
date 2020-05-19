package com.cloud.payment.controller;

import com.cloud.api.common.domain.CommonResult;
import com.cloud.api.common.domain.Payment;
import com.cloud.payment.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public CommonResult save(@RequestBody Payment payment) {
        int result = paymentService.save(payment);
        log.info("save payment {} and return result {}", payment, result);

        if (result > 0) {
            return new CommonResult(200, "insert success, server port:" + serverPort, payment);
        } else {
            return new CommonResult(444, "insert failed server port:" + serverPort, null);
        }
    }

    @GetMapping("/{id}")
    public CommonResult getById(@PathVariable Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("get payment {} a", payment);

        if (Objects.nonNull(payment)) {
            return new CommonResult(200, "get success server port:" + serverPort, payment);
        } else {
            return new CommonResult(444, "Not found for id " + id, null);
        }
    }

    @GetMapping("/port")
    public String getPort() {
        return serverPort;
    }

}
