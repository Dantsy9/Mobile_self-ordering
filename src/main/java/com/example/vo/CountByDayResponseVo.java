package com.example.vo;

import lombok.Data;


@Data
public class CountByDayResponseVo {

    /**
     * 金额
     */
    private Double actual;

    /**
     * 日期
     */
    private String dayTime;

}
