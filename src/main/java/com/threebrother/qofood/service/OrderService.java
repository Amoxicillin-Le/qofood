package com.threebrother.qofood.service;

import com.threebrother.qofood.model.DTO.OrderDetailDTO;
import com.threebrother.qofood.model.PO.GoodsPO;
import com.threebrother.qofood.model.PageInfo;

public interface OrderService {

    String createOrder(GoodsPO goodsPO);

    OrderDetailDTO getOrderDetailInfo(String userOpenId, String orderId);

    PageInfo<OrderDetailDTO> getOrderDetailListByUserOpenIdAndOrderStatus(String userOpenId, int orderStatus, int pageNum, int pageSize);

    void updateOrderLogistics(String orderId, String userOpenId, int receiveAddressId);
}
