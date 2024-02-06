package com.example.entity;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class Cart {
    /** ID */
    private Integer id;
    /** 商品ID */
    private Integer goodsId;
    /** 数量 */
    private Integer num;
    /** 用户ID */
    private Integer userId;
    private Integer businessId;

    private Business business;

    private Goods goods;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }
}
