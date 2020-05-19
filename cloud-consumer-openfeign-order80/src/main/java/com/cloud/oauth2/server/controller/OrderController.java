package com.cloud.oauth2.server.controller;

import com.cloud.api.common.domain.CommonResult;
import com.cloud.oauth2.server.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/{id}")
    public CommonResult get(@PathVariable Long id) {
        return orderService.getById(id);
    }

}
