package com.example.mallorders.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mallorders.entity.OrdersEntity;
import com.example.util.PageUtils;

import java.util.List;
import java.util.Map;

public interface OrdersService extends IService<OrdersEntity> {
    PageUtils queryPage(Map<String, Object> params);

    List<OrdersEntity> listByUserId(Integer userId);
}
