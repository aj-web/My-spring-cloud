package com.example.mallorders.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
/**
 * @Author ninan
 * @Description
 * @Date  2021/4/2
 **/
@Data
@TableName("t_order")
public class OrdersEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    private Integer id;
    /**
     *
     */
    private String userId;
    /**
     * 商品编号
     */
    private String commodityCode;
    /**
     *
     */
    private Integer count;
    /**
     *
     */
    private Integer amount;
}
