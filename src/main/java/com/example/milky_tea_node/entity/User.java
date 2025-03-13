package com.example.milky_tea_node.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;


@TableName("milky_tea_user")
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField
    private String avatar;
    @TableField
    private String username;
    @TableField
    private String password;
    @TableField
    private String phone;
    @TableField
    private String email;

    @TableField
    private String token;

    @TableField
    private String address;

    public User(String avatar, String username, String password, String phone, String email, String token, String address) {
        this.avatar = avatar;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.token = token;
        this.address = address;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", avatar='" + avatar + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", token='" + token + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
