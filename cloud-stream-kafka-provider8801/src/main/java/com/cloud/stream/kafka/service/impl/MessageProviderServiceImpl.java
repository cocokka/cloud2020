package com.cloud.stream.kafka.service.impl;

import cn.hutool.core.util.IdUtil;
import com.cloud.stream.kafka.service.MessageProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

@EnableBinding(Source.class)
public class MessageProviderServiceImpl implements MessageProviderService {

    @Autowired
    private MessageChannel messageChannel;

    @Override
    public void send() {

        messageChannel.send(MessageBuilder.withPayload(IdUtil.randomUUID()).build());

    }
}
