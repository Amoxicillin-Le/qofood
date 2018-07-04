package com.threebrother.qofood.model.PO;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

public class UpdateOrderLogisticsPO implements Serializable {

    String orderId;

    @NotBlank(message = "用户openId不能为空")
    String userOpenId;

    String receiveAddressId;

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

    public String getReceiveAddressId() {
        return receiveAddressId;
    }

    public void setReceiveAddressId(String receiveAddressId) {
        this.receiveAddressId = receiveAddressId;
    }
}
