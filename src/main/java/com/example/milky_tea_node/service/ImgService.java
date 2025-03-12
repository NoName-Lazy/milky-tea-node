package com.example.milky_tea_node.service;

import com.example.milky_tea_node.entity.User;
import com.example.milky_tea_node.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

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

        // 设置保存路径（资源目录下的路径）
        String uploadDir = "uploads/avatars";
        String originalFileName = image.getOriginalFilename();
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String newFileName = UUID.randomUUID().toString() + fileExtension; // 生成唯一的文件名
        Path uploadPath = Paths.get(uploadDir, newFileName);

        // 确保目录存在
        try {
            Files.createDirectories(uploadPath.getParent());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create directory", e);
        }

        try {
            // 保存文件
            Files.copy(image.getInputStream(), uploadPath);

            // 更新用户信息
            user.setAvatar(uploadPath.toString()); // 假设 User 实体类中有 avatar 字段
            userMapper.updateById(user); // 使用 MyBatis-Plus 更新用户信息

            return user;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to upload image", e);
        }
    }

    public boolean checkFileExists(String fileName) {
        Path path = Paths.get("uploads/avatars/" + fileName);
        return Files.exists(path);
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = Paths.get("uploads/avatars/" + fileName);
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("File not found " + fileName);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error loading file");
        }
    }
}