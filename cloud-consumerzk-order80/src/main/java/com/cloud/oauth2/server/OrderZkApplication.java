package com.cloud.oauth2.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OrderZkApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderZkApplication.class, args);
    }
}
