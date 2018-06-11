package com.threebrother.qofood.entity;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.sql.Timestamp;

public class ReceiveAddress implements Serializable {

    private Integer receiveAddressId;

    private String receiveAddressContent;

    @NotBlank(message = "收件地址邮编不能为空")
    private String receiveAddressZipCode;

    @NotBlank(message = "收件地址联系人姓名不能为空")
    private String receiveAddressContactName;

    @NotBlank(message = "收件地址联系人电话不能为空")
    private String receiveAddressContactPhone;

    private String userOpenId;

    private Boolean isDefault;

    @NotBlank(message = "收件地址省信息不能为空")
    private String receiveAddressProvince;

    @NotBlank(message = "收件地址市信息不能为空")
    private String receiveAddressCity;

    private String receiveAddressArea;

    @NotBlank(message = "收件地址街道信息不能为空")
    private String receiveAddressStreet;

    private Timestamp createTime;

    private Timestamp updateTime;

    public Integer getReceiveAddressId() {
        return receiveAddressId;
    }

    public void setReceiveAddressId(Integer receiveAddressId) {
        this.receiveAddressId = receiveAddressId;
    }

    public String getReceiveAddressContent() {
        return receiveAddressContent;
    }

    public void setReceiveAddressContent(String receiveAddressContent) {
        this.receiveAddressContent = receiveAddressContent;
    }

    public String getReceiveAddressZipCode() {
        return receiveAddressZipCode;
    }

    public void setReceiveAddressZipCode(String receiveAddressZipCode) {
        this.receiveAddressZipCode = receiveAddressZipCode;
    }

    public String getReceiveAddressContactName() {
        return receiveAddressContactName;
    }

    public void setReceiveAddressContactName(String receiveAddressContactName) {
        this.receiveAddressContactName = receiveAddressContactName;
    }

    public String getReceiveAddressContactPhone() {
        return receiveAddressContactPhone;
    }

    public void setReceiveAddressContactPhone(String receiveAddressContactPhone) {
        this.receiveAddressContactPhone = receiveAddressContactPhone;
    }

    public String getUserOpenId() {
        return userOpenId;
    }

    public void setUserOpenId(String userOpenId) {
        this.userOpenId = userOpenId;
    }

    public Boolean getDefault() {
        return isDefault;
    }

    public void setDefault(Boolean aDefault) {
        isDefault = aDefault;
    }

    public String getReceiveAddressProvince() {
        return receiveAddressProvince;
    }

    public void setReceiveAddressProvince(String receiveAddressProvince) {
        this.receiveAddressProvince = receiveAddressProvince;
    }

    public String getReceiveAddressCity() {
        return receiveAddressCity;
    }

    public void setReceiveAddressCity(String receiveAddressCity) {
        this.receiveAddressCity = receiveAddressCity;
    }

    public String getReceiveAddressArea() {
        return receiveAddressArea;
    }

    public void setReceiveAddressArea(String receiveAddressArea) {
        this.receiveAddressArea = receiveAddressArea;
    }

    public String getReceiveAddressStreet() {
        return receiveAddressStreet;
    }

    public void setReceiveAddressStreet(String receiveAddressStreet) {
        this.receiveAddressStreet = receiveAddressStreet;
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
