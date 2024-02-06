package com.example.dto.res;

import lombok.Data;

/**
 * @Author: zhuangmch
 * @CreateTime: 2024-02-06
 * @Description: 月统计结果
 */
@Data
public class CountByMonthResponseDto {

    /**
     * 商家昵称
     **/
    private String businessName;

    /**
     * 统计的金额
     **/
    private String actual;

}
