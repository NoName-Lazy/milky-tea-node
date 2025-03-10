package com.example.milky_tea_node.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.milky_tea_node.entity.User;
import com.example.milky_tea_node.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class UserService {
    private static final String UPLOAD_DIR = "uploads/images/";
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

    public void insertToken(User user) {

        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", user.getId());

        userMapper.update(user, updateWrapper);
    }


    public User GetProfile(Integer id) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        return userMapper.selectOne(queryWrapper);
    }

    public Boolean Register(User newuser) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("id", newuser.getId());
        if (userMapper.selectOne(queryWrapper) == null) {
            userMapper.insert(newuser);
            return true;
        }
        return false;
    }

    public User Modify_Password(Integer id, String password) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id);

        User updateUser = new User();
        updateUser.setPassword(password);

        userMapper.update(updateUser, updateWrapper);

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        return userMapper.selectOne(queryWrapper);
    }

    public User Modify_Username(Integer id, String username) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id);

        User updateUser = new User();
        updateUser.setUsername(username);

        userMapper.update(updateUser, updateWrapper);

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        return userMapper.selectOne(queryWrapper);
    }
}

