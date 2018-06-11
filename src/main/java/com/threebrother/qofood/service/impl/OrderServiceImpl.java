package com.threebrother.qofood.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.base.Strings;
import com.threebrother.qofood.common.Constant;
import com.threebrother.qofood.common.RequestConstant;
import com.threebrother.qofood.common.exception.BusinessException;
import com.threebrother.qofood.dao.*;
import com.threebrother.qofood.entity.*;
import com.threebrother.qofood.model.DTO.OrderDetailDTO;
import com.threebrother.qofood.model.Enum.OrderStatusEnum;
import com.threebrother.qofood.model.Enum.PreferentialStrategyTypeEnum;
import com.threebrother.qofood.model.PO.GoodsPO;
import com.threebrother.qofood.model.PageInfo;
import com.threebrother.qofood.service.OrderService;
import com.threebrother.qofood.util.OrderIdUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {

    private Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    OrderDetailMapper orderDetailMapper;

    @Autowired
    PreferentialStrategyMapper preferentialStrategyMapper;

    @Autowired
    ReceivreAddressMapper receivreAddressMapper;

    @Autowired
    OrderLogisticsMapper orderLogisticsMapper;

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public String createOrder(GoodsPO goodsPO) {

        BigDecimal shouldPaymentAmount = new BigDecimal(BigInteger.ZERO);
        BigDecimal actualPaymentAmount = new BigDecimal(BigInteger.ZERO);
        BigDecimal freightAmount = new BigDecimal(BigInteger.ZERO);


        // 获取订单优惠策略
        Integer preferentialStrategyType = preferentialStrategyMapper.getPreferentialStrategyTypeById();
        if(null == preferentialStrategyType){
            throw new BusinessException(
                    RequestConstant.CREATE_ORDER_FAILE_PREFERENTIAL_STRATEGY_INVALID_CODE,
                    RequestConstant.CREATE_ORDER_FAILE_PREFERENTIAL_STRATEGY_INVALID_MSG);
        }

        // 获取订单编号
        String orderId = OrderIdUtil.getOrderNo();
        logger.info("创建订单编号 ===> " + orderId);



        // 构建订单详情对象
        List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();

        List<GoodsPO.GoodsModel> goodsModelList = goodsPO.getGoodsList();
        for(GoodsPO.GoodsModel goodsModel : goodsModelList) {

            Goods goods = goodsMapper.selectByGoodsId(goodsModel.getGoodsId());
            if (null == goods) {
                throw new BusinessException(
                        RequestConstant.CREATE_ORDER_FAILE_GOODS_ID_INVALID_CODE,
                        RequestConstant.CREATE_ORDER_FAILE_GOODS_ID_INVALID_MSG);
            }

            OrderDetail orderDetail = new OrderDetail();

            orderDetail.setGoodsId(goods.getGoodsId());
            orderDetail.setGoodsNum(goodsModel.getGoodsNum());
            orderDetail.setGoodsSmallImageUrl(goods.getGoodsSmallImageUrl());
            orderDetail.setGoodsPrice(goods.getGoodsPrice());
            orderDetail.setGoodsName(goods.getGoodsName());
            orderDetail.setGoodsDesc(goods.getGoodsDesc());

            if (goodsPO.getTotalNum() >= Constant.INT_TWO  && PreferentialStrategyTypeEnum.SINGLE_PRICE_DISCOUNT.getValue() == preferentialStrategyType) {

                BigDecimal goodsPreferentialPrice = goods.getGoodsPrice().subtract(Constant.DEFAULT_FREIGHT_AMOUNT);
                orderDetail.setGoodsPreferentialPrice(goodsPreferentialPrice);
            } else {
                orderDetail.setGoodsPreferentialPrice(goods.getGoodsPrice());
            }

            orderDetail.setTotalFree(orderDetail.getGoodsPrice().multiply(new BigDecimal(orderDetail.getGoodsNum())));
            // 累计添加 应付款
            shouldPaymentAmount = shouldPaymentAmount.add(orderDetail.getTotalFree());
            orderDetail.setTotalPreferentialFree(orderDetail.getGoodsPreferentialPrice().multiply(new BigDecimal(orderDetail.getGoodsNum())));
            // 累计添加 实际需要付款
            actualPaymentAmount = actualPaymentAmount.add(orderDetail.getTotalPreferentialFree());
            orderDetail.setOrderId(orderId);

            orderDetails.add(orderDetail);
        }

        // 批量保存订单详情
        orderDetailMapper.insertBatchByOrderDetailList(orderDetails);


        // 指定订单收件地址Id
        ReceiveAddress receiveAddress = receivreAddressMapper.selectDefaultReceiceAddressByUserOpenId(goodsPO.getUserOpenId());
        if(null != receiveAddress && !Strings.isNullOrEmpty(receiveAddress.getReceiveAddressProvince())){

            // 处理偏远地区邮费+5元
            for (String s : Constant.SPECIAL_AREA) {
                if (receiveAddress.getReceiveAddressProvince().contains(s)) {
                    freightAmount.add(new BigDecimal(5));
                }

            }

            OrderLogistics orderLogistics = new OrderLogistics();
            orderLogistics.setOrderId(orderId);
            orderLogistics.setReceiveContactName(receiveAddress.getReceiveAddressContactName());
            orderLogistics.setReceiveContactPhone(receiveAddress.getReceiveAddressContactPhone());
            orderLogistics.setReceiveZipCode(receiveAddress.getReceiveAddressZipCode());
            orderLogistics.setReceiveProvince(receiveAddress.getReceiveAddressProvince());
            orderLogistics.setRececiceCity(receiveAddress.getReceiveAddressCity());
            orderLogistics.setReceiveArea(receiveAddress.getReceiveAddressArea());
            orderLogistics.setReceiveStreet(receiveAddress.getReceiveAddressStreet());

            // 建立订单和物流的关联关系
            orderLogisticsMapper.insertOrderLogistics(orderLogistics);
        }

        // 构建订单
        Order order = new Order();

        order.setOrderId(orderId);
        order.setShouldPaymentAmount(shouldPaymentAmount.add(freightAmount));

        // TODO 这里实现满减 目前嫌麻烦未实现 后面实现
        //if(PreferentialStrategyTypeEnum.BUY_UP_AND_REDUCE.getValue() == PreferentialStrategyTypeEnum.SINGLE_PRICE_DISCOUNT.getValue()){
        //    actualPaymentAmount
        //}

        order.setActualPaymentAmount(actualPaymentAmount.add(freightAmount));
        order.setFreightAmount(freightAmount);
        order.setOrderStatus(OrderStatusEnum.NOT_PAYMENT.getValue());
        order.setUserOpenId(goodsPO.getUserOpenId());

        orderMapper.insertOrder(order);

        return orderId;
    }

    @Override
    @Transactional(readOnly = true)
    public OrderDetailDTO getOrderDetailInfo(String userOpenId, String orderId) {

        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();

        Order order = orderMapper.selectOrderByOrderIdAndUserOpenId(userOpenId, orderId);
        orderDetailDTO.setOrderInfo(order);

        List<OrderDetail> orderDetails = orderDetailMapper.selectOrderDetailListByOrderId(orderId);
        orderDetailDTO.setOrderDetail(orderDetails);

        OrderLogistics orderLogistics = new OrderLogistics();
        orderLogistics = orderLogisticsMapper.selectOrderLogisticsByOrderId(orderId);
        orderDetailDTO.setOrderLogisticsInfo(orderLogistics);

        List<ReceiveAddress> receiveAddresses = new ArrayList<>();
        receiveAddresses = receivreAddressMapper.selectreceiveAddressListByUserOpenIdGroupByIsDefaultAndCreateTime(userOpenId);
        orderDetailDTO.setReceiveAddressList(receiveAddresses);

        return orderDetailDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public PageInfo<OrderDetailDTO> getOrderDetailListByUserOpenIdAndOrderStatus(String userOpenId, int orderStatus, int pageNum, int pageSize) {

        if(null == OrderStatusEnum.getEnumByValue(orderStatus)){
            throw new BusinessException(
                    RequestConstant.SELECT_ORDER_FAILE_ORDER_STATUS_INVALID_CODE,
                    RequestConstant.SELECT_ORDER_FAILE_ORDER_STATUS_INVALID_MSG);
        }

        PageHelper.startPage(pageNum, pageSize);
        Page<Order> orderPage = orderMapper.selectOrderListByUserOpenIdAndOrderStatus(userOpenId, orderStatus);
        PageInfo<Order> orderPageInfo = new PageInfo<>(orderPage);

        List<OrderDetailDTO> orderDetailDTOS = new ArrayList<>();
        for (Order order : orderPage) {
            OrderDetailDTO orderDetailDTO = new OrderDetailDTO();

            orderDetailDTO.setOrderInfo(order);

            List<OrderDetail> orderDetails = orderDetailMapper.selectOrderDetailListByOrderId(order.getOrderId());
            orderDetailDTO.setOrderDetail(orderDetails);

            OrderLogistics orderLogistics = new OrderLogistics();
            orderLogistics = orderLogisticsMapper.selectOrderLogisticsByOrderId(order.getOrderId());
            orderDetailDTO.setOrderLogisticsInfo(orderLogistics);

            List<ReceiveAddress> receiveAddresses = new ArrayList<>();
            receiveAddresses = receivreAddressMapper.selectreceiveAddressListByUserOpenIdGroupByIsDefaultAndCreateTime(userOpenId);
            orderDetailDTO.setReceiveAddressList(receiveAddresses);

            orderDetailDTOS.add(orderDetailDTO);
        }

        PageInfo<OrderDetailDTO> orderDetailDTOPageInfo = new PageInfo<>();
        orderDetailDTOPageInfo.setIsFirstPage(orderPageInfo.isIsFirstPage());
        orderDetailDTOPageInfo.setIsLastPage(orderPageInfo.isIsLastPage());
        orderDetailDTOPageInfo.setList(orderDetailDTOS);
        orderDetailDTOPageInfo.setPageNum(orderPageInfo.getPageNum());
        orderDetailDTOPageInfo.setPageSize(orderPageInfo.getPageSize());
        orderDetailDTOPageInfo.setTotal(orderPageInfo.getTotal());
        orderDetailDTOPageInfo.setPages(orderPageInfo.getPages());

        return orderDetailDTOPageInfo;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateOrderLogistics(String orderId, String userOpenId, int receiveAddressId) {

        ReceiveAddress receiveAddress = receivreAddressMapper.selectReceiveAddressByreceiveAddressIdAndUserOpenId(receiveAddressId, userOpenId);

        if (null == receiveAddress) {
            throw new BusinessException(
                    RequestConstant.UPDATE_ORDER_LOGISTICS_FAILE_RECEIVE_ADDRESS_ID_INVALID_CODE,
                    RequestConstant.UPDATE_ORDER_LOGISTICS_FAILE_RECEIVE_ADDRESS_ID_INVALID_MSG);
        }

        // 处理偏远地区邮费 +5 元
        boolean isfree = true;
        for (String s : Constant.SPECIAL_AREA) {
            if (receiveAddress.getReceiveAddressProvince().contains(s)) {
                isfree = false;
                continue;
            }
        }

        Order order = orderMapper.selectOrderByOrderIdAndUserOpenId(userOpenId, orderId);

        // 选择的地址不免费
        if (!isfree) {
            if (!(order.getFreightAmount().compareTo(Constant.DEFAULT_FREIGHT_AMOUNT) == Constant.INT_ZERO)) {
                order.setFreightAmount(Constant.DEFAULT_FREIGHT_AMOUNT);

                BigDecimal shouldPaymentAmount = order.getShouldPaymentAmount().add(Constant.DEFAULT_FREIGHT_AMOUNT);
                order.setShouldPaymentAmount(shouldPaymentAmount);

                BigDecimal actualPaymentAmount = order.getActualPaymentAmount().add(Constant.DEFAULT_FREIGHT_AMOUNT);
                order.setActualPaymentAmount(actualPaymentAmount);

                orderMapper.updateOrderAmountByOrder(order);
            }
        }
        // 选择的地址免费
        if (isfree) {
            if (!(order.getFreightAmount().compareTo(Constant.BIGDECIMAL_ZERO) == Constant.INT_ZERO)) {

                order.setFreightAmount(Constant.BIGDECIMAL_ZERO);

                BigDecimal shouldPaymentAmount = order.getShouldPaymentAmount().subtract(Constant.DEFAULT_FREIGHT_AMOUNT);
                order.setShouldPaymentAmount(shouldPaymentAmount);

                BigDecimal actualPaymentAmount = order.getActualPaymentAmount().subtract(Constant.DEFAULT_FREIGHT_AMOUNT);
                order.setActualPaymentAmount(actualPaymentAmount);

                orderMapper.updateOrderAmountByOrder(order);
            }
        }

        OrderLogistics orderLogistics = orderLogisticsMapper.selectOrderLogisticsByOrderId(orderId);

        boolean isSave = false;
        if(null == orderLogistics){
            isSave = true;
            orderLogistics = new OrderLogistics();
            orderLogistics.setOrderId(orderId);
        }

        orderLogistics.setReceiveContactName(receiveAddress.getReceiveAddressContactName());
        orderLogistics.setReceiveContactPhone(receiveAddress.getReceiveAddressContactPhone());
        orderLogistics.setReceiveZipCode(receiveAddress.getReceiveAddressZipCode());
        orderLogistics.setReceiveProvince(receiveAddress.getReceiveAddressProvince());
        orderLogistics.setRececiceCity(receiveAddress.getReceiveAddressCity());
        orderLogistics.setReceiveArea(receiveAddress.getReceiveAddressArea());
        orderLogistics.setReceiveStreet(receiveAddress.getReceiveAddressStreet());

        if(isSave){
            orderLogisticsMapper.insertOrderLogistics(orderLogistics);
        }else {
            orderLogisticsMapper.updateOrderLogistics(orderLogistics);
        }
    }

    @Override
    @Transactional
    public void deleteOrderByUserOpenIdAndOrderId(String userOpenId, String orderId) {

        // 获取该订单
        Order order = orderMapper.selectOrderByOrderIdAndUserOpenId(userOpenId, orderId);
        if (null == order || order.getOrderStatus() != Constant.INT_ONE) {
            throw new BusinessException(RequestConstant.DELETE_ORDER_FAILE_CODE, RequestConstant.DELETE_ORDER_FAILE_MSG);
        }

        // 删除订单详情
        // 删除订单物流
        // 删除订单
        // 或者
        // 订单设置为已删除即可
        orderMapper.updateOrderIsDeleteByOrderId(orderId);
    }
}
