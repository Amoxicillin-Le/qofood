package com.threebrother.qofood.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class Order implements Serializable {

    private String orderId;

    private BigDecimal shouldPaymentAmount;

    private BigDecimal actualPaymentAmount;

    private BigDecimal freightAmount;

    private Integer orderStatus;

    private Boolean isDelete;

    private String userOpenId;

    private String orderRemarks;

    private String extend;

    private String logisticsName;

    private String logisticsConsignNo;

    private Timestamp paymentTime;

    private Timestamp consignTime;

    private Timestamp createTime;

    private Timestamp updateTime;

    private Timestamp deleteTime;

    public Order() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getShouldPaymentAmount() {
        return shouldPaymentAmount;
    }

    public void setShouldPaymentAmount(BigDecimal shouldPaymentAmount) {
        this.shouldPaymentAmount = shouldPaymentAmount;
    }

    public BigDecimal getActualPaymentAmount() {
        return actualPaymentAmount;
    }

    public void setActualPaymentAmount(BigDecimal actualPaymentAmount) {
        this.actualPaymentAmount = actualPaymentAmount;
    }

    public BigDecimal getFreightAmount() {
        return freightAmount;
    }

    public void setFreightAmount(BigDecimal freightAmount) {
        this.freightAmount = freightAmount;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public String getUserOpenId() {
        return userOpenId;
    }

    public void setUserOpenId(String userOpenId) {
        this.userOpenId = userOpenId;
    }

    public String getOrderRemarks() {
        return orderRemarks;
    }

    public void setOrderRemarks(String orderRemarks) {
        this.orderRemarks = orderRemarks;
    }

    public String getExtend() {
        return extend;
    }

    public void setExtend(String extend) {
        this.extend = extend;
    }

    public String getLogisticsName() {
        return logisticsName;
    }

    public void setLogisticsName(String logisticsName) {
        this.logisticsName = logisticsName;
    }

    public String getLogisticsConsignNo() {
        return logisticsConsignNo;
    }

    public void setLogisticsConsignNo(String logisticsConsignNo) {
        this.logisticsConsignNo = logisticsConsignNo;
    }

    public Timestamp getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Timestamp paymentTime) {
        this.paymentTime = paymentTime;
    }

    public Timestamp getConsignTime() {
        return consignTime;
    }

    public void setConsignTime(Timestamp consignTime) {
        this.consignTime = consignTime;
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

    public Timestamp getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Timestamp deleteTime) {
        this.deleteTime = deleteTime;
    }
}
