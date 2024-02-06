package com.example.entity;

import lombok.Getter;

import java.io.Serializable;

/**
 * 商品分类
 */
@Getter
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    /** 菜品分类名称 */
    private String name;
    /** 商家ID */
    private Integer businessId;

    private  String businessName;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }
}
