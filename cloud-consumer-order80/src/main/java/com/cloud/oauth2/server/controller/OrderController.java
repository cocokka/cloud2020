package com.cloud.oauth2.server.controller;

import com.cloud.api.common.domain.CommonResult;
import com.cloud.api.common.domain.Payment;
import com.cloud.oauth2.server.config.LoadBalanceRule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Value("${payment-service.url}")
    private String paymentServiceUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private LoadBalanceRule loadBalanceRule;

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public CommonResult save(@RequestBody Payment payment) {
        HttpEntity httpEntity = new HttpEntity(payment);
        ResponseEntity<CommonResult> exchange = this.restTemplate.exchange(paymentServiceUrl, HttpMethod.POST, httpEntity, CommonResult.class);
        log.info("save payment {} and return result {}", payment, exchange);
        return exchange.getBody();
    }

    @GetMapping("/{id}")
    public CommonResult get(@PathVariable Long id) {
        ResponseEntity<CommonResult> exchange = this.restTemplate.getForEntity(paymentServiceUrl + "/" + id, CommonResult.class);
        log.info("get payment by id {} and return result {}", id, exchange);
        return exchange.getBody();
    }


    @GetMapping("/lb/{id}")
    public CommonResult loadBalance(@PathVariable Long id) {
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        ServiceInstance serviceInstance = loadBalanceRule.choose(instances);
        URI uri = serviceInstance.getUri();
        log.info("current service is {}", uri);
        return restTemplate.getForObject(uri + "/payment/" + id, CommonResult.class);
    }

}
