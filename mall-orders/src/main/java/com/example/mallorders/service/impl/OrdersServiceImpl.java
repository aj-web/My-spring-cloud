package com.example.mallorders.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mallorders.dao.OrdersDao;
import com.example.mallorders.entity.OrdersEntity;
import com.example.mallorders.service.OrdersService;
import com.example.util.PageUtils;
import com.example.util.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("orderService")
public class OrdersServiceImpl extends ServiceImpl<OrdersDao, OrdersEntity> implements OrdersService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrdersEntity> page = this.page(
                new Query<OrdersEntity>().getPage(params),
                new QueryWrapper<OrdersEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public List<OrdersEntity> listByUserId(Integer userId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id",userId);
        return baseMapper.selectList(queryWrapper);
    }

}
