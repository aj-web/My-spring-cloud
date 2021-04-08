package com.example.usercenter.controller;

import com.example.usercenter.openservice.OrderFeignService;
import com.example.usercenter.service.RemoteService;
import feign.Feign;
import feign.Request;
import feign.Retryer;
import feign.codec.Decoder;
import feign.codec.Encoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @description:
 * @author: bangsun
 * @date: 2021/4/8 16:57
 */
@RestController
@Slf4j
@RequestMapping("/customer")
public class customer {

    @Autowired
    OrderFeignService orderFeignService;

    RestTemplate restTemplate = new RestTemplate();
    /**
     * @Author ninan
     * @Description 使用RestTemple调用
     * @Date  2021/4/8
     **/
    @RequestMapping("/WantBuy/{num}")
    public String WantBuyBangBang(@PathVariable int num){
        String s = restTemplate.getForObject("http://localhost:8889/producter/buy/"+num,String.class);
        return  s;
    }

    /**
     * 单独使用Fegin调用
     * @param num
     * @return
     */
    @RequestMapping(value = "/FeignBuy/{num}")
    public String FeignBuy(@PathVariable int num){
        RemoteService remoteService = Feign.builder().encoder(new Encoder.Default())
                .decoder(new Decoder.Default())
                .options(new Request.Options(1000, 3500))
                .retryer(new Retryer.Default(5000, 5000, 3))
                .target(RemoteService.class, "http://localhost:8889/");
        return remoteService.buy(num);
    }

    /**
     * 使用openfeign调用远程服务
     * @param num
     * @return
     */
    @RequestMapping(value = "/OpenFeignBuy/{num}")
    public String OpenFeignBuy(@PathVariable int num){
        return orderFeignService.OpenFeignBuy(num);
    }
}
