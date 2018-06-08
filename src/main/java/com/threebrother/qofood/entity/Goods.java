package com.threebrother.qofood.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class Goods implements Serializable {

    private Integer goodsId;

    private String goodsName;

    private String goodsDesc;

    private BigDecimal goodsPrice;

    private Integer goodsSalesVolume;

    private String goodsSmallImageUrl;

    private String goodsBig1ImageUrl;

    private String goodsBig2ImageUrl;

    private String goodsBig3ImageUrl;

    private Integer goodsCount;

//    private Integer goodsSalesVolume;

    private Timestamp createTime;

    private Timestamp updateTime;

    public Goods() {
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Integer getGoodsSalesVolume() {
        return goodsSalesVolume;
    }

    public void setGoodsSalesVolume(Integer goodsSalesVolume) {
        this.goodsSalesVolume = goodsSalesVolume;
    }

    public String getGoodsSmallImageUrl() {
        return goodsSmallImageUrl;
    }

    public void setGoodsSmallImageUrl(String goodsSmallImageUrl) {
        this.goodsSmallImageUrl = goodsSmallImageUrl;
    }

    public String getGoodsBig1ImageUrl() {
        return goodsBig1ImageUrl;
    }

    public void setGoodsBig1ImageUrl(String goodsBig1ImageUrl) {
        this.goodsBig1ImageUrl = goodsBig1ImageUrl;
    }

    public String getGoodsBig2ImageUrl() {
        return goodsBig2ImageUrl;
    }

    public void setGoodsBig2ImageUrl(String goodsBig2ImageUrl) {
        this.goodsBig2ImageUrl = goodsBig2ImageUrl;
    }

    public String getGoodsBig3ImageUrl() {
        return goodsBig3ImageUrl;
    }

    public void setGoodsBig3ImageUrl(String goodsBig3ImageUrl) {
        this.goodsBig3ImageUrl = goodsBig3ImageUrl;
    }

    public Integer getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
    }

//    public Integer getGoodsSalesVolume() {
//        return goodsSalesVolume;
//    }

//    public void setGoodsSalesVolume(Integer goodsSalesVolume) {
//        this.goodsSalesVolume = goodsSalesVolume;
//    }

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
        return "Goods{" +
                "goodsId=" + goodsId +
                ", goodsName='" + goodsName + '\'' +
                ", goodsDesc='" + goodsDesc + '\'' +
                ", goodsPrice=" + goodsPrice +
                ", goodsSmallImageUrl='" + goodsSmallImageUrl + '\'' +
                ", goodsBig1ImageUrl='" + goodsBig1ImageUrl + '\'' +
                ", goodsBig2ImageUrl='" + goodsBig2ImageUrl + '\'' +
                ", goodsBig3ImageUrl='" + goodsBig3ImageUrl + '\'' +
                ", goodsCount='" + goodsCount + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
