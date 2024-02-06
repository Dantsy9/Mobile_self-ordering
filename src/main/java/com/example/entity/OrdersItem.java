package com.example.entity;

import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单详情表
 */
@Getter
public class OrdersItem implements Serializable {
    private static final long serialVersionUID = 1L;

    /**  */
    private Integer id;
    /** 订单ID */
    private Integer orderId;
    /** 商品名称 */
    private String goodsName;
    /** 商品图片 */
    private String goodsImg;
    /** 商品价格 */
    private BigDecimal price;
    /** 购买数量 */
    private BigDecimal num;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setNum(BigDecimal num) {
        this.num = num;
    }

}
