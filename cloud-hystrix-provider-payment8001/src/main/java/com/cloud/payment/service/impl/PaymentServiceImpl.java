package com.cloud.payment.service.impl;

import cn.hutool.core.util.IdUtil;
import com.cloud.api.common.domain.Payment;
import com.cloud.payment.dao.PaymentDao;
import com.cloud.payment.service.PaymentService;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDao paymentDao;

    @Override
    public int save(Payment payment) {
        return paymentDao.save(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }

    @Override
//    @HystrixCommand(fallbackMethod = "fallbackHandler", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
//    })
    public String timeout() throws Exception {
        int timeout = 2;

        TimeUnit.SECONDS.sleep(timeout);
        log.info("request processed");

        return String.format("timeout after %s seconds.", timeout);
    }

    @Override
    public String exception() throws Exception {
//        int r = 10 / 0;
        return String.format("exception.");
    }

    private String fallbackHandler() {
        return String.format("fallback.");
    }


    // ========服务熔断

    /**
     * @see HystrixCommandProperties
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "fallbackHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000"),
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
    })
    public String circuitBreaker(Integer id) {
        if (id < 0) {
            throw new RuntimeException("id should greater than 0");
        }

        String uuid = IdUtil.randomUUID();
        return uuid;
    }

    private String fallbackHandler(Integer id) {
        return String.format("fallback. due to id-{} is less than 0", id);
    }
}
