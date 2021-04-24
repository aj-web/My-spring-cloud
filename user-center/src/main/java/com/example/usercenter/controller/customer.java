package com.example.usercenter.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.Tracer;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
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

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

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

    private static final String RESOURCE_NAME = "hello";

    @RequestMapping("/helloSentinel")
    public String hello() {
        Entry entry = null;
        try {

            entry = SphU.entry(RESOURCE_NAME);
            //业务逻辑
            String s = "Hello Sentinel";
            return s;

        } catch (BlockException e) {
            log.info("BLOCK!");
        } catch (Exception e) {
            Tracer.traceEntry(e, entry);
        }finally {
            if (entry != null) {
                entry.exit();
            }
        }
        return null;
    }

    /**
     * 定义流控规则
     */
    @PostConstruct
    private static void initFlowRules(){
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        //设置受保护的资源
        rule.setResource(RESOURCE_NAME);
        // 设置流控规则 QPS
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // 设置受保护的资源阈值
        // Set limit QPS to 20.
        rule.setCount(1);
        rules.add(rule);
        // 加载配置好的规则
        FlowRuleManager.loadRules(rules);
    }
}
