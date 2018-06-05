package com.threebrother.qofood.dao;

import com.threebrother.qofood.entity.OrderLogistics;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderLogisticsMapper {
    void insertOrderLogistics(OrderLogistics orderLogistics);

    OrderLogistics selectOrderLogisticsByOrderId(String orderId);

    void updateOrderLogistics(OrderLogistics orderLogistics);
}
