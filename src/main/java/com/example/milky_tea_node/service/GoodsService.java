package com.example.milky_tea_node.service;

import com.example.milky_tea_node.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsService {

    @Autowired
    GoodsMapper goodsMapper;
}
