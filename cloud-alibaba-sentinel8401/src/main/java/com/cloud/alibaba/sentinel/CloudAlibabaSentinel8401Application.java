package com.cloud.alibaba.sentinel;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CloudAlibabaSentinel8401Application {

    public static void main(String[] args) {
        SpringApplication.run(CloudAlibabaSentinel8401Application.class, args);
    }
}
