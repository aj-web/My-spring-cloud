package com.example.malluser.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.malluser.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author ninan
 * @Description
 * @Date 2021/4/2
 **/
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {

}
