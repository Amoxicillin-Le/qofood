package com.threebrother.qofood.model.PO;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

public class UpdateOrderLogisticsPO implements Serializable {

    String orderId;

    @NotBlank(message = "用户openId不能为空")
    String userOpenId;

    @NotBlank(message = "收件地址Id不能为空")
    Integer receiveAddressId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserOpenId() {
        return userOpenId;
    }

    public void setUserOpenId(String userOpenId) {
        this.userOpenId = userOpenId;
    }

    public Integer getReceiveAddressId() {
        return receiveAddressId;
    }

    public void setReceiveAddressId(Integer receiveAddressId) {
        this.receiveAddressId = receiveAddressId;
    }
}
