package com.caito.payrollservice.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeansInyectorConfig {

    @Bean
    public RestTemplate restTemplate() throws Exception {
        return new RestTemplate();
    }
}
