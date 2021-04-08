package com.example.usercenter.openservice;

import com.example.usercenter.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author ninan
 * @Description feign的接口
 * @Date  2021/4/8
 **/
@FeignClient(value = "producter-center" ,path = "/producter")
public interface OrderFeignService {

    @RequestMapping("/Buy/{num}")
    public String OpenFeignBuy(@PathVariable int num);
}
