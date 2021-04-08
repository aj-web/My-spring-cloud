package com.example.usercenter.config;

import feign.Logger;
import feign.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 注意： 此处配置@Configuration注解就会全局生效，如果想指定对应微服务生效，就不能配置
@Configuration
public class FeignConfig {
    /**
     * 日志级别
     *
     * @return
     */
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    /**
     * 超时时间配置
     * @return
     */
    @Bean
    public Request.Options options() {
        return new Request.Options(5000, 10000);
    }

    /*@Bean
    public Decoder decoder() {
        return new JacksonDecoder();
    }
    @Bean
    public Encoder encoder() {
        return new JacksonEncoder();
    }*/
}
