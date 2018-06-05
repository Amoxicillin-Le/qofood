package com.threebrother.qofood.entity;

import java.sql.Timestamp;

public class OrderLogistics {

    private Integer orderLogisticsId;

    private String orderId;

    private String receiveContactName;

    private String receiveContactPhone;

    private String receiveZipCode;

    private String receiveProvince;

    private String receciceCity;

    private String receiveArea;

    private String receiveStreet;

    private Timestamp createTime;

    private Timestamp updateTime;

    public OrderLogistics() {
    }

    public Integer getOrderLogisticsId() {
        return orderLogisticsId;
    }

    public void setOrderLogisticsId(Integer orderLogisticsId) {
        this.orderLogisticsId = orderLogisticsId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getReceiveContactName() {
        return receiveContactName;
    }

    public void setReceiveContactName(String receiveContactName) {
        this.receiveContactName = receiveContactName;
    }

    public String getReceiveContactPhone() {
        return receiveContactPhone;
    }

    public void setReceiveContactPhone(String receiveContactPhone) {
        this.receiveContactPhone = receiveContactPhone;
    }

    public String getReceiveZipCode() {
        return receiveZipCode;
    }

    public void setReceiveZipCode(String receiveZipCode) {
        this.receiveZipCode = receiveZipCode;
    }

    public String getReceiveProvince() {
        return receiveProvince;
    }

    public void setReceiveProvince(String receiveProvince) {
        this.receiveProvince = receiveProvince;
    }

    public String getRececiceCity() {
        return receciceCity;
    }

    public void setRececiceCity(String receciceCity) {
        this.receciceCity = receciceCity;
    }

    public String getReceiveArea() {
        return receiveArea;
    }

    public void setReceiveArea(String receiveArea) {
        this.receiveArea = receiveArea;
    }

    public String getReceiveStreet() {
        return receiveStreet;
    }

    public void setReceiveStreet(String receiveStreet) {
        this.receiveStreet = receiveStreet;
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
}
