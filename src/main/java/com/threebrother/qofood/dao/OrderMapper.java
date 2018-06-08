package com.threebrother.qofood.dao;

import com.github.pagehelper.Page;
import com.threebrother.qofood.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderMapper {

    void insertOrder(Order order);

    Order selectOrderByOrderIdAndUserOpenId(@Param("userOpenId") String userOpenId, @Param("orderId")String orderId);

    Page<Order> selectOrderListByUserOpenIdAndOrderStatus(@Param("userOpenId") String userOpenId, @Param("orderStatus") Integer orderStatus);

    void updateOrderAmountByOrder(Order order);
}
