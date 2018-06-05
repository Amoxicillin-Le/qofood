package com.threebrother.qofood.model.DTO;

import com.threebrother.qofood.entity.Order;
import com.threebrother.qofood.entity.OrderDetail;
import com.threebrother.qofood.entity.OrderLogistics;
import com.threebrother.qofood.entity.ReceiveAddress;

import java.io.Serializable;
import java.util.List;

public class OrderDetailDTO implements Serializable {

    Order orderInfo;

    OrderLogistics orderLogisticsInfo;

    List<OrderDetail> orderDetail;

    List<ReceiveAddress> receiveAddressList;

    public OrderDetailDTO() {
    }

    public Order getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(Order orderInfo) {
        this.orderInfo = orderInfo;
    }

    public OrderLogistics getOrderLogisticsInfo() {
        return orderLogisticsInfo;
    }

    public void setOrderLogisticsInfo(OrderLogistics orderLogisticsInfo) {
        this.orderLogisticsInfo = orderLogisticsInfo;
    }

    public List<OrderDetail> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<OrderDetail> orderDetail) {
        this.orderDetail = orderDetail;
    }

    public List<ReceiveAddress> getReceiveAddressList() {
        return receiveAddressList;
    }

    public void setReceiveAddressList(List<ReceiveAddress> receiveAddressList) {
        this.receiveAddressList = receiveAddressList;
    }
}
