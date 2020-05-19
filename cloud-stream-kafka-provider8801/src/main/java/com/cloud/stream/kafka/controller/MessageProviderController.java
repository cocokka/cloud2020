package com.cloud.stream.kafka.controller;

import com.cloud.stream.kafka.service.MessageProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("message/provider")
public class MessageProviderController {

    @Autowired
    private MessageProviderService messageProviderService;

    @GetMapping("/send")
    public String send() {
        messageProviderService.send();
        return String.format("message send");
    }
}
