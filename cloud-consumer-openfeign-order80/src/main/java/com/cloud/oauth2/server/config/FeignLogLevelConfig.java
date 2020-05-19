package com.cloud.oauth2.server.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignLogLevelConfig {

    @Bean
    Logger.Level level() {
        return Logger.Level.FULL;
    }
}
