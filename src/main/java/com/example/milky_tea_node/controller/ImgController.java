package com.example.milky_tea_node.controller;

import com.example.milky_tea_node.entity.Request;
import com.example.milky_tea_node.entity.User;
import com.example.milky_tea_node.service.ImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class ImgController {
    Request request = new Request();
    @Autowired
    private ImgService imgService;

    @PatchMapping("/upload-image")
    public ResponseEntity<Request> uploadImage(@RequestParam("id") Integer id, @RequestParam("image") MultipartFile image) {
        try {
            User user = imgService.uploadImage(id, image);
            request.setUser(user);
            request.setCode(200);
            request.setMessage("Image uploaded successfully");
            return ResponseEntity.status(request.getCode()).body(request);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new Request(400, e.getMessage()));
        }
    }

    @GetMapping("/uploads/avatars/{fileName:.+}")
    public ResponseEntity<Resource> serveFile(@PathVariable String fileName) throws MalformedURLException {
        Path filePath = Paths.get("uploads/avatars/" + fileName);
        Resource resource = new UrlResource(filePath.toUri());
        if (resource.exists() || resource.isReadable()) {
            String contentType = determineContentType(fileName);
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private String determineContentType(String fileName) {
        if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) {
            return "image/jpeg";
        } else if (fileName.endsWith(".png")) {
            return "image/png";
        }
        // 添加其他文件类型的判断
        return "application/octet-stream"; // 默认类型
    }
}

