package com.threebrother.qofood.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.threebrother.qofood.common.Constant;
import com.threebrother.qofood.common.RequestConstant;
import com.threebrother.qofood.common.exception.BusinessException;
import com.threebrother.qofood.model.DTO.OrderDetailDTO;
import com.threebrother.qofood.model.PO.GoodsPO;
import com.threebrother.qofood.model.PO.UpdateOrderLogisticsPO;
import com.threebrother.qofood.model.PageInfo;
import com.threebrother.qofood.model.Result;
import com.threebrother.qofood.service.OrderService;
import com.threebrother.qofood.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@Api(description = "订单--相关接口")
public class OrderController {

    @Autowired
    OrderService orderService;

    @ApiOperation(value = "创建订单", notes = "创建未付款订单")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", dataType = "GoodsPO", name = "goodsPO", value = "订单", required = true)
    })
    @RequestMapping(value = "/order/save", method = {RequestMethod.POST})
    @ResponseBody
    public Result<JSONObject> createOrder(@RequestBody GoodsPO goodsPO){

        String orderId = orderService.createOrder(goodsPO);

        JSONObject data = new JSONObject();
        data.put(Constant.ORDER_ID, orderId);

        return ResultUtil.success(data);
    }


    @ApiOperation(value = "获取订单详情", notes = "根据用户OpenId、订单Id；获取订单详情")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "userOpenId", value = "用户OpenId", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "orderId", value = "订单Id", required = true)
    })
    @RequestMapping(value = "/order", method = {RequestMethod.GET})
    @ResponseBody
    public Result<OrderDetailDTO> getOrderDetailInfo(@RequestParam String userOpenId, @RequestParam String orderId){

        OrderDetailDTO orderDetailDTO = orderService.getOrderDetailInfo(userOpenId, orderId);

        return ResultUtil.success(orderDetailDTO);
    }


    @ApiOperation(value = "获取订单列表", notes = "根据用户OpenId、订单状态；获取用户该状态的订单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "userOpenId", value = "用户OpenId", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "orderStatus", value = "订单状态 (1-未付款；2-已付款；3-已发货)", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "Integer", name = "pageNum", value = "当前页数", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "Integer", name = "pageSize", value = "每页记录数", required = true)
    })
    @RequestMapping(value = "/orders", method = {RequestMethod.GET})
    @ResponseBody
    public Result<PageInfo<OrderDetailDTO>> getOrderList(@RequestParam String userOpenId, @RequestParam int orderStatus,
                                                     @RequestParam int pageNum, @RequestParam int pageSize){

        PageInfo<OrderDetailDTO> orderDetailDTOPageInfo = orderService.getOrderDetailListByUserOpenIdAndOrderStatus(userOpenId, orderStatus, pageNum, pageSize);

        return ResultUtil.success(orderDetailDTOPageInfo);
    }


    @ApiOperation(value = "修改订单物流信息", notes = "根据订单Id，用户OpenId、收件地址Id；更新该用户该订单的物流信息，并重新计算订单运费及相关费用")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "orderId", value = "订单Id", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "userOpenId", value = "用户OpenId", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "receiveAddressId", value = "收件地址Id", required = true)
    })
    @RequestMapping(value = "/order/updateLogistics", method = {RequestMethod.POST})
    @ResponseBody
    public Result updateOrderLogistics(@Valid @RequestBody UpdateOrderLogisticsPO updateOrderLogisticsPO, BindingResult bindingResult){

        // 参数校验
        if(Strings.isNullOrEmpty(updateOrderLogisticsPO.getOrderId())){
            throw new BusinessException(RequestConstant.UPDATE_ORDER_RECEIVE_ADDRESS_FAILE_CODE,
                    RequestConstant.UPDATE_ORDER_RECEIVE_ADDRESS_FAILE_MSG);
        }

        if (bindingResult.hasErrors()) {
            throw new BusinessException(RequestConstant.UPDATA_RECEIVE_ADDRESS_FAILE_CODE,
                    RequestConstant.UPDATE_ORDER_RECEIVE_ADDRESS_FAILE_MSG + bindingResult.getFieldError().getDefaultMessage());
        }


        orderService.updateOrderLogistics(updateOrderLogisticsPO.getOrderId(),
                updateOrderLogisticsPO.getUserOpenId(), updateOrderLogisticsPO.getReceiveAddressId());

        return ResultUtil.success();
    }


    @ApiOperation(value = "删除未付款订单", notes = "根据用户OpenId，收件地址Id，删除该未付款订单")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "userOpenId", value = "用户OpenId", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "orderId", value = "订单Id", required = true),
    })
    @RequestMapping(value = "/order/delete", method = {RequestMethod.GET})
    @ResponseBody
    public Result deleteUnpaidOrderByUserOpenIdAndOrderId(@RequestParam String userOpenId, @RequestParam String orderId){

        orderService.deleteOrderByUserOpenIdAndOrderId(userOpenId, orderId);

        return ResultUtil.success();
    }

}
