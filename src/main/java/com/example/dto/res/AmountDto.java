package com.example.dto.res;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class AmountDto {
//    总价
    private BigDecimal amount;
//    优惠金额
    private BigDecimal discount;
//    实际价格
    private BigDecimal actual;

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public void setActual(BigDecimal actual) {
        this.actual = actual;
    }
}
