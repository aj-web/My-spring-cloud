package com.example.malluser.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Author ninan
 * @Description
 * @Date  2021/4/2
 **/
@Configuration
public class RestConfig {
    @Bean
    public RestTemplate restTemplate() {

        return new RestTemplate();
    }
}
