package com.example.milky_tea_node.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.UUID;

@TableName("milky_tea_goods")
public class Goods {
    @TableId(type = IdType.ASSIGN_UUID)
    private String goods_id;//商品id

    @TableField
    private String shop_id;//店铺id

    @TableField
    private String shop_name;//店铺名称

    @TableField
    private String goods_type;//商品类型

    @TableField
    private String goods_name;//商品名称

    @TableField
    private String goods_image;//商品图片

    @TableField
    private float goods_price;//商品价格

    @TableField
    private String goods_desc;//商品描述

    public Goods(String shop_id, String shop_name, String goods_type, String goods_name, String goods_image, float goods_price, String goods_desc) {
        this.goods_id = UUID.randomUUID().toString();
        this.shop_id = shop_id;
        this.shop_name = shop_name;
        this.goods_type = goods_type;
        this.goods_name = goods_name;
        this.goods_image = goods_image;
        this.goods_price = goods_price;
        this.goods_desc = goods_desc;
    }

    public Goods() {
        this.goods_id = UUID.randomUUID().toString();
    }

    public String getGoods_id() {
        return goods_id;
    }


    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getGoods_type() {
        return goods_type;
    }

    public void setGoods_type(String goods_type) {
        this.goods_type = goods_type;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getGoods_image() {
        return goods_image;
    }

    public void setGoods_image(String goods_image) {
        this.goods_image = goods_image;
    }

    public float getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(float goods_price) {
        this.goods_price = goods_price;
    }

    public String getGoods_desc() {
        return goods_desc;
    }

    public void setGoods_desc(String goods_desc) {
        this.goods_desc = goods_desc;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "goods_id='" + goods_id + '\'' +
                ", shop_id='" + shop_id + '\'' +
                ", shop_name='" + shop_name + '\'' +
                ", goods_type='" + goods_type + '\'' +
                ", goods_name='" + goods_name + '\'' +
                ", goods_image='" + goods_image + '\'' +
                ", goods_price=" + goods_price +
                ", goods_desc='" + goods_desc + '\'' +
                '}';
    }
}
