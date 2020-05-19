package com.cloud.loadbalance;


import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RuleConfig {

    @Bean
    public RandomRule randomRule() {
        return new RandomRule();
    }

}
