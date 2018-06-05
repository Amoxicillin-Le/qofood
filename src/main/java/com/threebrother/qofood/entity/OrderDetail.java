package com.threebrother.qofood.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class OrderDetail implements Serializable {

    private Integer orderDetailId;

    private Integer goodsId;

    private Integer goodsNum;

    private String goodsSmallImageUrl;

    private BigDecimal goodsPrice;

    private BigDecimal goodsPreferentialPrice;

    private BigDecimal totalFree;

    private BigDecimal totalPreferentialFree;

    private String orderId;

    private Timestamp createTime;

    private Boolean isRemove;

    private Timestamp removeTime;

    public OrderDetail() {
    }

    public Integer getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Integer orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsSmallImageUrl() {
        return goodsSmallImageUrl;
    }

    public void setGoodsSmallImageUrl(String goodsSmallImageUrl) {
        this.goodsSmallImageUrl = goodsSmallImageUrl;
    }

    public BigDecimal getTotalFree() {
        return totalFree;
    }

    public void setTotalFree(BigDecimal totalFree) {
        this.totalFree = totalFree;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Boolean getRemove() {
        return isRemove;
    }

    public void setRemove(Boolean remove) {
        isRemove = remove;
    }

    public Timestamp getRemoveTime() {
        return removeTime;
    }

    public void setRemoveTime(Timestamp removeTime) {
        this.removeTime = removeTime;
    }

    public BigDecimal getGoodsPreferentialPrice() {
        return goodsPreferentialPrice;
    }

    public void setGoodsPreferentialPrice(BigDecimal goodsPreferentialPrice) {
        this.goodsPreferentialPrice = goodsPreferentialPrice;
    }

    public BigDecimal getTotalPreferentialFree() {
        return totalPreferentialFree;
    }

    public void setTotalPreferentialFree(BigDecimal totalPreferentialFree) {
        this.totalPreferentialFree = totalPreferentialFree;
    }
}
