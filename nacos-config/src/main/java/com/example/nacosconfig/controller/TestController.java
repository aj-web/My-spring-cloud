package com.example.nacosconfig.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope  // 替换配置bean
public class TestController {

    @Value("${common.age}")
    private String age;
    @Value("${common.name}")
    private String name;

    @GetMapping("/common")
    public String hello() {
        return name+","+age;
    }


}