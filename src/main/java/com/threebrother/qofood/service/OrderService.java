package com.threebrother.qofood.service;

import com.threebrother.qofood.model.DTO.OrderDetailDTO;
import com.threebrother.qofood.model.PO.GoodsPO;

import java.util.List;

public interface OrderService {

    String createOrder(GoodsPO goodsPO);

    OrderDetailDTO getOrderDetailInfo(String userOpenId, String orderId);

    List<OrderDetailDTO> getOrderDetailListByUserOpenIdAndOrderStatus(String userOpenId, int orderStatus);

    void updateOrderLogistics(String orderId, String userOpenId, int receiveAddressId);
}
