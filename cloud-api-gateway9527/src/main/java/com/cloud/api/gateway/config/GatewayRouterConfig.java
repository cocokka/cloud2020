package com.cloud.api.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayRouterConfig {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder) {

        return routeLocatorBuilder
                .routes()
                .route("baidu_news", r -> r.path("/guoji").uri("http://news.baidu.com/guoji"))
                .build();

    }
}
