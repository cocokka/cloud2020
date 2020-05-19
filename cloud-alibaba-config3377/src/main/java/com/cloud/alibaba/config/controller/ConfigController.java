package com.cloud.alibaba.config.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
@RefreshScope
public class ConfigController {

    @Value("${server.port}")
    private String port;

    @Value("${conf.info}")
    private String confInfo;

    @GetMapping("/port")
    public String port() {
        return port + "|" + confInfo;
    }

}
