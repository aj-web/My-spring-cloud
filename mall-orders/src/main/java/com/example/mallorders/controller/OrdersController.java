package com.example.mallorders.controller;


import java.util.Arrays;
import java.util.List;
import java.util.Map;


import com.example.mallorders.entity.OrdersEntity;
import com.example.mallorders.service.OrdersService;
import com.example.util.PageUtils;
import com.example.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



/**
 *
 *
 * @author fox
 * @email 2763800211@qq.com
 * @date 2021-01-28 15:46:19
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    /**
     * 根据用户id查询订单信息
     * @param userId
     * @return
     */
    @RequestMapping("/findOrderByUserId/{userId}")
    public R findOrderByUserId(@PathVariable("userId") Integer userId) {

//        try {
//            Thread.sleep(8000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        //模拟异常
//        if(userId==5){
//            throw new IllegalArgumentException("非法参数异常");
//        }

        log.info("根据userId:"+userId+"查询订单信息");
        List<OrdersEntity> orderEntities = ordersService.listByUserId(userId);
        return R.ok().put("orders", orderEntities);
    }

    /**
     * 测试gateway
     * @param request
     * @return
     * @throws Exception
     */
//    @GetMapping("/testgateway")
//    public String testGateway(HttpServletRequest request) throws Exception {
//        log.info("gateWay获取请求头X-Request-color："
//                +request.getHeader("X-Request-color"));
//        return "success";
//    }
//    @GetMapping("/testgateway2")
//    public String testGateway(@RequestHeader("X-Request-color") String color) throws Exception {
//        log.info("gateWay获取请求头X-Request-color："+color);
//        return "success";
//    }
//    @GetMapping("/testgateway3")
//    public String testGateway3(@RequestParam("color") String color) throws Exception {
//        log.info("gateWay获取请求参数color:"+color);
//        return "success";
//    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = ordersService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
        OrdersEntity order = ordersService.getById(id);
        return R.ok().put("order", order);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody OrdersEntity order){
        ordersService.save(order);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody OrdersEntity order){
        ordersService.updateById(order);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        ordersService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
