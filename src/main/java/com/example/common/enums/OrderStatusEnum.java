package com.example.common.enums;

//取消、支付、出餐、评价、完成、退款
public enum OrderStatusEnum {
//    CANCEL("已取消"),
    SERVE_MEALS("0","待出餐"),
    FINISH("1","已完成"),
    REFUND("2","已退款");

    private String code;
    private String value;

    OrderStatusEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
