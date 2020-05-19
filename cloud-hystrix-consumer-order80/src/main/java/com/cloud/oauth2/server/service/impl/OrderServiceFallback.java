package com.cloud.oauth2.server.service.impl;

import com.cloud.oauth2.server.service.OrderService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceFallback implements FallbackFactory<OrderService> {
    @Override
    public OrderService create(Throwable cause) {
        return new OrderService() {

            @Override
            public String timeout() {
                return String.format("OrderServiceFallback.timeout");
            }

            @Override
            public String exception() {
                return String.format("OrderServiceFallback.exception");
            }

        };
    }
}
