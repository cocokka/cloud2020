package com.cloud.oauth2.server.controller;

import com.cloud.oauth2.server.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@Slf4j
//@DefaultProperties(defaultFallback = "globalFallbackHandler")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/timeout")
//    @HystrixCommand(fallbackMethod = "fallbackHandler", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
//    })
    public String timeout() throws Exception {
        return orderService.timeout();
    }

    @GetMapping("/exception")
//    @HystrixCommand(fallbackMethod = "fallbackHandler", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
//    })
//    @HystrixCommand
    public String exception() throws Exception {
//        int a = 10 / 0;
        return orderService.exception();
    }


    private String fallbackHandler() {
        return String.format("i'm 80. error. please check server or self.");
    }

    private String globalFallbackHandler() {
        return String.format("i'm 80. global error. please check server or self.");
    }

}
