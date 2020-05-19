package com.cloud.payment;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import javax.servlet.Servlet;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableCircuitBreaker
public class PaymentHystrix8001Application {

    public static void main(String[] args) {
        SpringApplication.run(PaymentHystrix8001Application.class, args);
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        HystrixMetricsStreamServlet hystrixMetricsStreamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean<Servlet> servletServletRegistrationBean = new ServletRegistrationBean<>(hystrixMetricsStreamServlet);
        servletServletRegistrationBean.setLoadOnStartup(1);
        servletServletRegistrationBean.addUrlMappings("/hystrix.stream");
        servletServletRegistrationBean.setName("HystrixMetricsStreamServlet");
        return servletServletRegistrationBean;
    }
}
