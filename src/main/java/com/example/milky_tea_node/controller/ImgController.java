package com.example.milky_tea_node.controller;

import com.example.milky_tea_node.utils.ImageUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class ImgController {
    private static final String UPLOAD_DIR = "uploads/";

    @PostMapping("/uploadimage")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file,
                                              @RequestParam(value = "compress", defaultValue = "false") boolean compress,
                                              @RequestParam(value = "maxWidthOrHeight", defaultValue = "1024") int maxWidthOrHeight) {
        try {
            // 检查文件是否为空
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("文件不能为空");
            }

            // 保存原始文件
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            File originalFile = new File(uploadPath.toFile(), file.getOriginalFilename());
            file.transferTo(originalFile);

            File finalFile;
            if (compress) {
                // 压缩图片
                finalFile = ImageUtils.compressImage(originalFile, maxWidthOrHeight);
            } else {
                finalFile = originalFile;
            }

            // 返回图片的访问路径
            String imageUrl = "/api/image/" + finalFile.getName();
            return ResponseEntity.ok(imageUrl);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("上传失败");
        }
    }

    @GetMapping("/{filename}")
    public ResponseEntity<byte[]> getImage(@PathVariable String filename) {
        try {
            Path filePath = Paths.get(UPLOAD_DIR + filename);
            byte[] imageBytes = Files.readAllBytes(filePath);
            return ResponseEntity.ok(imageBytes);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(404).body("图片未找到".getBytes());
        }
    }
}