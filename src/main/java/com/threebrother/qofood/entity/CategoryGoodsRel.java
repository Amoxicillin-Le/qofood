package com.threebrother.qofood.entity;

import java.io.Serializable;

public class CategoryGoodsRel implements Serializable {

    private Integer categoruGoodsRelId;

    private Integer categoruId;

    private Integer goodsId;

    public CategoryGoodsRel() {

    }

    public Integer getCategoruGoodsRelId() {
        return categoruGoodsRelId;
    }

    public void setCategoruGoodsRelId(Integer categoruGoodsRelId) {
        this.categoruGoodsRelId = categoruGoodsRelId;
    }

    public Integer getCategoruId() {
        return categoruId;
    }

    public void setCategoruId(Integer categoruId) {
        this.categoruId = categoruId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    @Override
    public String toString() {
        return "CategoryGoodsRel{" +
                "categoruGoodsRelId=" + categoruGoodsRelId +
                ", categoruId=" + categoruId +
                ", goodsId=" + goodsId +
                '}';
    }
}
