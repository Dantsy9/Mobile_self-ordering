package com.example.entity;

import lombok.Getter;

import java.io.Serializable;

/**
 * 广告信息
 */
@Getter
public class Banner implements Serializable {
    private static final long serialVersionUID = 1L;

    /** ID */
    private Integer id;
    /** 标题*/
    private String title;
    /** 图片 */
    private String img;
    /** 商家ID */
    private Integer businessId;

    private String businessName;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }
}