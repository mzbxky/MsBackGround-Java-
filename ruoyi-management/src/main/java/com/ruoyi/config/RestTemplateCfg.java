package com.ruoyi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateCfg {
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
