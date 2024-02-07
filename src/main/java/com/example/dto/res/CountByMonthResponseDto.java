package com.example.dto.res;

import lombok.Data;

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
