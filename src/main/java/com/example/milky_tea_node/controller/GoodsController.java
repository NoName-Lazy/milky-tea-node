package com.example.milky_tea_node.controller;

import com.example.milky_tea_node.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GoodsController {
    @Autowired
    GoodsService goodsService;
}

