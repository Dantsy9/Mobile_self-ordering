package com.example.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class Orders implements Serializable {
    private static final long serialVersionUID = 1L;

    /** ID */
    private Integer id;
    /** 订单编号 */
    private String orderNo;
    /** 桌号 */
    private Integer tableNum;
    /** 下单时间 */
    private String time;
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
    private Long actual;
    /** 备注 */
    private String comment;
    /** 封面 */
    private String cover;
    /** 名称 */
    private String name;

}
