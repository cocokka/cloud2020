package com.cloud.oauth2.server.config;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBalanceRule {

    ServiceInstance choose(List<ServiceInstance> serviceInstances);

}
