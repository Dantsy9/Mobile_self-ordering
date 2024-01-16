package com.example.vo;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CountByDayResponseVo {
    /**
     * 日期
     */
    private String dayTime;

    /**
     * 金额
     */
    private Long actual;
}
