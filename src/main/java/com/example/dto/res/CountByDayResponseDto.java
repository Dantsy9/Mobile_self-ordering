package com.example.dto.res;

import lombok.Data;


/**
 * @author V
 * 日期统计返回参数
 */
@Data
public class CountByDayResponseDto {
     /**
     * 日期
     */
    private String dayTime;

     /**
     * 金额
     */
     private Double actual;
}
