package com.example.milky_tea_node.controller;

import com.example.milky_tea_node.entity.Request;
import com.example.milky_tea_node.entity.User;
import com.example.milky_tea_node.service.UserService;
import com.example.milky_tea_node.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;


@RestController
public class UserController {
    @Autowired
    UserService userService;
    Request request = new Request();
    JwtUtils jwtUtils = new JwtUtils();

    @PostMapping("/login")
    public ResponseEntity<Request> login(@RequestParam("account") String account,
                                         @RequestParam("password") String password) {
        try {
            int flag = account.contains("@") ? 1 : 0;
            User user = userService.Login(account, password, flag);
            if (user != null) {

                String token = jwtUtils.generateToken(user.getId().toString());
                user.setToken(token);
                userService.insertToken(user);
                request.setCode(200);
                request.setMessage("登录成功");
                request.setUser(user);

            } else {
                request.setCode(401);
                request.setMessage("账号不存在或密码错误");
            }
        } catch (Exception e) {
            request.setCode(500);
            request.setMessage("服务器异常: " + e.getMessage());
            e.printStackTrace();
        }
        return ResponseEntity.status(request.getCode()).body(request);
    }

    @GetMapping("/profile")
    public ResponseEntity<Request> GetProfile(@RequestParam("id") Integer id) {
        try {
            User user = userService.GetProfile(id);
            request.setCode(200);
            request.setMessage("获取用户信息成功");
            request.setUser(user);
        } catch (Exception e) {
            request.setCode(500);
            request.setMessage("服务器异常: " + e.getMessage());
            e.printStackTrace();
        }
        return ResponseEntity.status(request.getCode()).body(request);
    }

    @GetMapping("/logout")
    public ResponseEntity<Request> Logout() {
        request.setCode(200);
        request.setMessage("登出成功");
        return ResponseEntity.status(request.getCode()).body(request);
    }

    @PostMapping("/register")
    public ResponseEntity<Request> Register(@RequestParam("account") String account,
                                            @RequestParam("password") String password,
                                            @RequestParam("address") String address,
                                            @RequestParam("type") String type) {
        User user = userService.Register(account, address, password, type);
        try {
            if (user != null) {
                request.setCode(200);
                request.setMessage("注册成功");
                request.setUser(user);
            } else {
                request.setCode(401);
                request.setMessage("账号已存在");
            }
        } catch (Exception e) {
            request.setCode(500);
            request.setMessage("服务器异常: " + e.getMessage());
            e.printStackTrace();
        }
        return ResponseEntity.status(request.getCode()).body(request);
    }

    @PatchMapping("/modify-password")
    public ResponseEntity<Request> Modify_Password(@RequestParam("id") Integer id, @RequestParam("password") String password) {
        try {
            User user = userService.Modify_Password(id, password);
            request.setUser(user);
            request.setCode(200);
            request.setMessage("密码修改成功");
        } catch (Exception e) {
            request.setCode(500);
            request.setMessage("服务器异常: " + e.getMessage());
            e.printStackTrace();
        }
        return ResponseEntity.status(request.getCode()).body(request);
    }

    @PatchMapping("/modify-username")
    public ResponseEntity<Request> Modify_Username(@RequestParam("id") Integer id, @RequestParam("username") String username) {
        try {
            User user = userService.Modify_Username(id, username);
            request.setUser(user);
            request.setCode(200);
            request.setMessage("用户名修改成功");
        } catch (Exception e) {
            request.setCode(500);
            request.setMessage("服务器异常: " + e.getMessage());
            e.printStackTrace();
        }
        return ResponseEntity.status(request.getCode()).body(request);
    }

    @PatchMapping("/modify-email")
    public ResponseEntity<Request> Modify_Email(@RequestParam("id") Integer id, @RequestParam("email") String email) {
        try {
            User user = userService.Modify_Email(id, email);
            if (user != null) {
                request.setUser(user);
                request.setCode(200);
                request.setMessage("邮箱修改成功");
            } else {
                request.setCode(401);
                request.setMessage("该邮箱已被使用");
            }

        } catch (Exception e) {
            request.setCode(500);
            request.setMessage("服务器异常: " + e.getMessage());
            e.printStackTrace();
        }
        return ResponseEntity.status(request.getCode()).body(request);
    }

    @PatchMapping("/modify-phone")
    public ResponseEntity<Request> Modify_Phone(@RequestParam("id") Integer id, @RequestParam("phone") String phone) {
        try {
            User user = userService.Modify_Phone(id, phone);
            if (user != null) {
                request.setUser(user);
                request.setCode(200);
                request.setMessage("手机号修改成功");
            } else {
                request.setCode(401);
                request.setMessage("该手机号已被使用");
            }

        } catch (Exception e) {
            request.setCode(500);
            request.setMessage("服务器异常: " + e.getMessage());
            e.printStackTrace();
        }
        return ResponseEntity.status(request.getCode()).body(request);
    }
}
