package com.example.milky_tea_node.service;

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

@Service
public class ImgService {
    @Autowired
    UserMapper userMapper;

    public User uploadImage(Integer id, MultipartFile image) {
        // 检查用户是否存在
        User user = userMapper.selectById(id);
        if (user == null) {
            throw new IllegalArgumentException("User not found with ID: " + id);
        }

        // 检查文件是否为空
        if (image.isEmpty()) {
            throw new IllegalArgumentException("No file uploaded");
        }

        // 检查文件类型是否为图片
        String contentType = image.getContentType();
        if (!contentType.startsWith("image")) {
            throw new IllegalArgumentException("Invalid file type. Only images are allowed");
        }

        // 设置保存路径（可以根据需要修改为动态路径）
        String uploadDir = "uploads/avatars";
        String fileName = image.getOriginalFilename();
        Path uploadPath = Paths.get(uploadDir, fileName);

        // 创建目录（如果不存在）
        File uploadDirFile = new File(uploadDir);
        if (!uploadDirFile.exists()) {
            uploadDirFile.mkdirs();
        }

        try {
            // 保存文件
            Files.copy(image.getInputStream(), uploadPath);

            // 更新用户信息
            user.setAvatar(uploadPath.toString()); // 假设 User 实体类中有 profileImagePath 字段
            userMapper.updateById(user); // 使用 MyBatis-Plus 更新用户信息

            return user;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to upload image", e);
        }
    }
}
