package com.example.dto;

import lombok.Getter;

@Getter
public class OrdersDTO {

    private Integer businessId;
//    订单桌号
    private Integer tableNum;
//    订单备注
    private String remark;

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public void setTableNum(Integer tableNum) {
        this.tableNum = tableNum;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
