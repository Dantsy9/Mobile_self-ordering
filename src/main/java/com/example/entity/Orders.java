package com.example.entity;

import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
public class Orders implements Serializable {
    private static final long serialVersionUID = 1L;

    /** ID */
    private Integer id;
    /** 订单编号 */
    private String orderNo;
    /** 桌号 */
    private Integer tableNum;
    /** 支付时间 */
    private String payTime;
    /** 订单状态 */
    private String status;
    /** 接单商家ID */
    private Integer businessId;
    /**商家名称 */
    private String businessName;
    /** 下单人电话 */
    private String phone;
    /** 下单人ID */
    private Integer userId;
    /**用户名 */
    private String userName;
    /** 订单总价 */
    private BigDecimal amount;
    /** 优惠金额 */
    private BigDecimal discount;
    /** 实付款 */
    private BigDecimal actual;
    /** 备注 */
    private String comment;
    /** 封面 */
    private String cover;
    /** 名称 */
    private String name;

    /** 评论ID*/
    private Integer commentId;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public void setTableNum(Integer tableNum) {
        this.tableNum = tableNum;
    }

    public String interceptPayTime() {
        return getPayTime().substring(0, 11);
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public void setActual(BigDecimal actual) {
        this.actual = actual;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }
}
