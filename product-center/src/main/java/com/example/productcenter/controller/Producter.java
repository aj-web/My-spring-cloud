package com.example.productcenter.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 提供服务
 * @author: bangsun
 * @date: 2021/4/8 16:55
 */
@RestController
@Slf4j
@RequestMapping("/producter")
public class Producter {


    @RequestMapping(value = "/Buy/{num}")
    public String SellBangBangTang(@PathVariable int num){
        return "你已经买到了"+num+"根棒棒糖啦";
    }


}
