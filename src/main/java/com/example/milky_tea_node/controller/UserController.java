package com.example.milky_tea_node.controller;

import com.example.milky_tea_node.entity.Request;
import com.example.milky_tea_node.entity.User;
import com.example.milky_tea_node.service.UserService;
import com.example.milky_tea_node.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
    public ResponseEntity<Request> getProfile() {
        try {
            User user = userService.Profile();
            request.setUser(user);
            request.setCode(200);
            request.setMessage("获取用户数据成功");
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

    @PatchMapping("/modify-password")
    public ResponseEntity<Request> Modify_Password(@RequestParam("id") Integer id, @RequestParam("password") String password) {
        try {
//            System.out.println(id + password);
            User newuser = userService.Modify_Password(id, password);
            request.setUser(newuser);
            request.setCode(200);
            request.setMessage("密码修改成功");
        } catch (Exception e) {
            request.setCode(500);
            request.setMessage("服务器异常: " + e.getMessage());
            e.printStackTrace();
        }
        return ResponseEntity.status(request.getCode()).body(request);
    }

    @PatchMapping("/rename")
    public ResponseEntity<Request> Rename(@RequestParam("id") Integer id, @RequestParam("username") String username) {
        try {
            User newuser = userService.Rename(id, username);
            request.setUser(newuser);
            request.setCode(200);
            request.setMessage("用户名修改成功");
        } catch (Exception e) {
            request.setCode(500);
            request.setMessage("服务器异常: " + e.getMessage());
            e.printStackTrace();
        }
        return ResponseEntity.status(request.getCode()).body(request);
    }
}
