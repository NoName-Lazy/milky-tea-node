package com.example.milky_tea_node.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.milky_tea_node.entity.User;
import com.example.milky_tea_node.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public User Login(String account, String password, int flag) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (flag == 0) {
            queryWrapper.eq("phone", account);
        } else {
            queryWrapper.eq("email", account);
        }
        queryWrapper.eq("password", password);
        return userMapper.selectOne(queryWrapper);
    }

    public User Profile(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        return userMapper.selectOne(queryWrapper);
    }

}
