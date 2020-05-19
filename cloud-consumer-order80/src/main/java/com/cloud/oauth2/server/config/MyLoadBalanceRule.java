package com.cloud.oauth2.server.config;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLoadBalanceRule implements LoadBalanceRule {


    private AtomicInteger counter = new AtomicInteger(0);

    private Integer getNextServerIndex() {

        int current;
        int next;

        for (; ; ) {
            current = counter.get();
            next = current >= Integer.MAX_VALUE ? 0 : current + 1;
            if (counter.compareAndSet(current, next)) {
                return next;
            }
        }
    }

    @Override
    public ServiceInstance choose(List<ServiceInstance> serviceInstances) {

        if (!CollectionUtils.isEmpty(serviceInstances)) {
            int size = serviceInstances.size();
            int index = this.getNextServerIndex() % size;

            return serviceInstances.get(index);
        }
        throw new RuntimeException("no service is running.");
    }
}
