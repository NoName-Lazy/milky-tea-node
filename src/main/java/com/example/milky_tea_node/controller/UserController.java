package com.example.milky_tea_node.controller;

import com.example.milky_tea_node.entity.Request;
import com.example.milky_tea_node.entity.User;
import com.example.milky_tea_node.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
public class UserController {
    @Autowired
    UserService userService;
    Request request = new Request();

    @PostMapping("/login")
    public ResponseEntity<Request> login(@RequestParam("account") String account,
                                         @RequestParam("password") String password) {
        try {
            int flag = account.contains("@") ? 1 : 0; // 判断是邮箱还是手机号
            User user = userService.Login(account, password, flag);
            if (user != null) {
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
}
