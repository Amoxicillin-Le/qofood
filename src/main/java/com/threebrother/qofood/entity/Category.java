package com.threebrother.qofood.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Category implements Serializable {

    private Integer categoryId;

    private String categoryName;

    private String categoryBackgroundUrl;

    private Boolean isShow;

    private Timestamp createTime;

    private Timestamp updateTime;

    public Category() {
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryBackgroundUrl() {
        return categoryBackgroundUrl;
    }

    public void setCategoryBackgroundUrl(String categoryBackgroundUrl) {
        this.categoryBackgroundUrl = categoryBackgroundUrl;
    }

    public Boolean getShow() {
        return isShow;
    }

    public void setShow(Boolean show) {
        isShow = show;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", categoryBackgroundUrl='" + categoryBackgroundUrl + '\'' +
                ", isShow=" + isShow +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}




