package com.example.milky_tea_node.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.milky_tea_node.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
