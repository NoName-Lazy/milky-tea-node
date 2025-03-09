package com.example.milky_tea_node.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
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

    public User Profile() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        return userMapper.selectOne(queryWrapper);
    }


    public User Modify_Password(Integer id, String newPassword) {
        if (id == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }

        // 创建 UpdateWrapper 对象
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id);

        // 创建要更新的对象
        User userToUpdate = new User();
        userToUpdate.setId(id);
        userToUpdate.setPassword(newPassword);

        // 执行更新操作
        int updateCount = userMapper.update(userToUpdate, updateWrapper);
        if (updateCount <= 0) {
            // 如果更新的记录数不大于0，表示没有记录被更新，可以抛出异常或返回null
            throw new IllegalArgumentException("No user found with ID: " + id);
        }

        // 更新成功后，重新获取更新后的用户信息
        return userMapper.selectById(id);
    }


    public User Rename(Integer id, String newUsername) {
        if (id == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }

        // 创建 UpdateWrapper 对象
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id);

        // 创建要更新的对象
        User userToUpdate = new User();
        userToUpdate.setId(id);
        userToUpdate.setUsername(newUsername);

        int updateCount = userMapper.update(userToUpdate, updateWrapper);
        if (updateCount <= 0) {
            // 如果更新的记录数不大于0，表示没有记录被更新，可以抛出异常或返回null
            throw new IllegalArgumentException("No user found with ID: " + id);
        }

        // 更新成功后，重新获取更新后的用户信息
        return userMapper.selectById(id);
    }
}
