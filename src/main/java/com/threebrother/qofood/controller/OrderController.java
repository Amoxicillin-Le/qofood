package com.threebrother.qofood.controller;

import com.alibaba.fastjson.JSONObject;
import com.threebrother.qofood.model.DTO.OrderDetailDTO;
import com.threebrother.qofood.model.PO.GoodsPO;
import com.threebrother.qofood.model.Result;
import com.threebrother.qofood.service.OrderService;
import com.threebrother.qofood.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/order/save")
    @ResponseBody
    public Result<String> createOrder(@RequestBody GoodsPO goodsPO){

        String orderId = orderService.createOrder(goodsPO);

        JSONObject data = new JSONObject();
        data.put("orderId", orderId);

        return ResultUtil.success(data);
    }

    @RequestMapping(value = "/order")
    @ResponseBody
    public Result<OrderDetailDTO> getOrderDetailInfo(@RequestParam String userOpenId, @RequestParam String orderId){

        OrderDetailDTO orderDetailDTO = orderService.getOrderDetailInfo(userOpenId, orderId);

        return ResultUtil.success(orderDetailDTO);
    }

    @RequestMapping(value = "/orders")
    @ResponseBody
    public Result<List<OrderDetailDTO>> getOrderList(@RequestParam String userOpenId, @RequestParam int orderStatus){

        List<OrderDetailDTO> orderDetailDTOS = orderService.getOrderDetailListByUserOpenIdAndOrderStatus(userOpenId, orderStatus);

        return ResultUtil.success(orderDetailDTOS);
    }

    @RequestMapping(value = "/oeder/updateLogistics")
    @ResponseBody
    public Result<OrderDetailDTO> updateOrderLogistics(@RequestParam String orderId, @RequestParam String userOpenId, @RequestParam int receiveAddressId){

        orderService.updateOrderLogistics(orderId, userOpenId, receiveAddressId);

        return ResultUtil.success();
    }

}
