package com.example.entity;

import lombok.Getter;

import java.io.Serializable;

/**
 * 评价表
 */
@Getter
public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;

    /** ID */
    private Integer id;
    /** 评论 */
    private String content;
    /** 评分 */
    private Double star;
    /** 用户ID */
    private Integer userId;
    /** 用户名 */
    private String userName;
    /** 商家ID */
    private Integer businessId;
    /** 商家名称 */
    private String businessName;
    /** 商品ID */
    private Integer goodsId;
    /** 订单ID */
    private Integer orderId;
    /** 订单编号 */
    private String orderNo;

    private String time;

    private String userAvatar;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setStar(Double star) {
        this.star = star;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }
}