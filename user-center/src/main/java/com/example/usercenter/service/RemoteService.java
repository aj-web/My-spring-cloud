package com.example.usercenter.service;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description:
 * @author: bangsun
 * @date: 2021/4/8 17:40
 */
public interface RemoteService {
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @RequestLine("GET /producter/Buy/{num}")
    public String buy(@Param("num") Integer num);
}
