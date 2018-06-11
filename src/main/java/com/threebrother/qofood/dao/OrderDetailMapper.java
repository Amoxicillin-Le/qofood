package com.threebrother.qofood.dao;

import com.threebrother.qofood.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderDetailMapper {

    void insertBatchByOrderDetailList(List<OrderDetail> orderDetails);

    List<OrderDetail> selectOrderDetailListByOrderId(String orderId);

}
