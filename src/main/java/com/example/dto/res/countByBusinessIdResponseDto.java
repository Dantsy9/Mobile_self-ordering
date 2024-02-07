package com.example.dto.res;

import lombok.Data;

@Data
public class countByBusinessIdResponseDto {

    /**
     * 菜品名称
     **/
    private String goodName;

    /**
     * 菜品销售数量
     **/
    private String goodsCount;

}
