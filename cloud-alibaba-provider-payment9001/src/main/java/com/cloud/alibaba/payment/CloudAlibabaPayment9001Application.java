package com.cloud.alibaba.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CloudAlibabaPayment9001Application {

    public static void main(String[] args) {
        SpringApplication.run(CloudAlibabaPayment9001Application.class, args);
    }
}
