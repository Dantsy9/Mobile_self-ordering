package com.example.entity;

import lombok.Getter;

import java.math.BigDecimal;

/**
 * 商品
 */
@Getter
public class Goods {
    /** ID */
    private Integer id;
    /** 名称 */
    private String name;
    /** 价格 */
    private BigDecimal price;
    /** 折后价格*/
    private BigDecimal actualPrice;
    /** 图片 */
    private String img;
    /** 描述 */
    private String descr;
    /** 口味 */
    private String taste;
    /** 上架日期 */
    private String date;
    /** 上架状态 */
    private String status;
    /** 折扣 */
    private Double discount;
    /** 商家ID */
    private Integer businessId;
    /** 分类ID */
    private Integer categoryId;
    private String businessName;
    private String categoryName;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public void setTaste(String taste) {
        this.taste = taste;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
    }
}
