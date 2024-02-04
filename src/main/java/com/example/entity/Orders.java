package com.example.entity;

import java.io.Serializable;
import java.math.BigDecimal;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getTableNum() {
        return tableNum;
    }

    public void setTableNum(Integer tableNum) {
        this.tableNum = tableNum;
    }

    public String getPayTime() {
        return payTime;
    }

    public String interceptPayTime() {
        return getPayTime().substring(0, 11);
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getActual() {
        return actual;
    }

    public void setActual(BigDecimal actual) {
        this.actual = actual;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
