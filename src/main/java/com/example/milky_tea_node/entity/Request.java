package com.example.milky_tea_node.entity;

public class Request {
    private int code;
    private String message;
    private User user;

    @Override
    public String toString() {
        return "Request{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", user=" + user +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Request() {
    }

    public Request(int code, String message, User user) {
        this.code = code;
        this.message = message;
        this.user = user;
    }
}
