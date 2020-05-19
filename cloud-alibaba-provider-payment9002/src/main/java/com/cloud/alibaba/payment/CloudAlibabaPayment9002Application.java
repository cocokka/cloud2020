package com.cloud.alibaba.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CloudAlibabaPayment9002Application {

    public static void main(String[] args) {
        SpringApplication.run(CloudAlibabaPayment9002Application.class, args);
    }
}
