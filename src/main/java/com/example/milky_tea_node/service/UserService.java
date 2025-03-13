package com.example.milky_tea_node.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.milky_tea_node.entity.User;
import com.example.milky_tea_node.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

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

    public User Register(String account, String address, String password, String type) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        User newuser = new User();
        newuser.setPassword(password);
        newuser.setAddress(address);
        if (Objects.equals(type, "phone")) {
            queryWrapper.eq("phone", account);
            if (userMapper.selectOne(queryWrapper) != null) {
                return null;
            } else {
                newuser.setPhone(account);
            }
        } else if (Objects.equals(type, "email")) {
            queryWrapper.eq("email", account);
            if (userMapper.selectOne(queryWrapper) != null) {
                return null;
            } else {
                newuser.setEmail(account);
            }
        }
        userMapper.insert(newuser);
        return FindByAccount(account, type);
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

    public User Modify_Email(Integer id, String email) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        if (userMapper.selectOne(queryWrapper) != null) {
            return null;
        } else {
            UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("id", id);

            User updateUser = new User();
            updateUser.setEmail(email);

            userMapper.update(updateUser, updateWrapper);

            queryWrapper.eq("id", id);
            return userMapper.selectOne(queryWrapper);
        }
    }

    public User Modify_Phone(Integer id, String phone) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", phone);
        if (userMapper.selectOne(queryWrapper) != null) {
            return null;
        } else {
            UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("id", id);

            User updateUser = new User();
            updateUser.setPhone(phone);

            userMapper.update(updateUser, updateWrapper);


            queryWrapper.eq("id", id);
            return userMapper.selectOne(queryWrapper);
        }
    }

    public User FindByAccount(String account, String type) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (Objects.equals(type, "phone")) {
            queryWrapper.eq("phone", account);
        } else if (Objects.equals(type, "email")) {
            queryWrapper.eq("email", account);
        }
        return userMapper.selectOne(queryWrapper);
    }
}

