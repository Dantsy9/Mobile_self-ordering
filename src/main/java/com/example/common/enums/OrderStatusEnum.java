package com.example.common.enums;

//取消、支付、出餐、评价、完成、退款
public enum OrderStatusEnum {
//    CANCEL("已取消"),
    SERVE_MEALS("待出餐"),
    FINISH("已完成"),
    REFUND("已退款");

    private String value;

    OrderStatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
