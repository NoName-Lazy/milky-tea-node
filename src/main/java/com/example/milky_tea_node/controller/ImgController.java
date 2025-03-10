package com.example.milky_tea_node.controller;

import com.example.milky_tea_node.entity.Request;
import com.example.milky_tea_node.entity.User;
import com.example.milky_tea_node.service.ImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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

}

