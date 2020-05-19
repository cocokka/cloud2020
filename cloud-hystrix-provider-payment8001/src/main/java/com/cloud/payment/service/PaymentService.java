package com.cloud.payment.service;

import com.cloud.api.common.domain.Payment;

public interface PaymentService {

    int save(Payment payment);

    Payment getPaymentById(Long id);

    String timeout() throws Exception;

    String exception() throws Exception;

    String circuitBreaker(Integer id);

}
