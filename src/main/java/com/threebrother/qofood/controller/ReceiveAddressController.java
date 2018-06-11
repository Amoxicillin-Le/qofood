package com.threebrother.qofood.controller;

import com.github.pagehelper.Page;
import com.google.common.base.Strings;
import com.threebrother.qofood.common.RequestConstant;
import com.threebrother.qofood.common.exception.BusinessException;
import com.threebrother.qofood.entity.ReceiveAddress;
import com.threebrother.qofood.model.PO.UpdateOrderLogisticsPO;
import com.threebrother.qofood.model.PageInfo;
import com.threebrother.qofood.model.Result;
import com.threebrother.qofood.service.ReceiveAddressService;
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

@Api(description = "收件地址--相关接口")
@Controller
public class ReceiveAddressController {

    @Autowired
    ReceiveAddressService receiveAddressService;


    @ApiOperation(value = "新增收件地址", notes = "新增收件地址")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", dataType = "ReceiveAddress", name = "receiveAddress", value = "收件地址", required = true)
    })
    @RequestMapping(value = "/receiveAddress/save", method = {RequestMethod.POST})
    @ResponseBody
    public Result saveReceiveAddress(@Valid @RequestBody ReceiveAddress receiveAddress, BindingResult bindingResult) {

        // 参数校验
        if (bindingResult.hasErrors()) {
            throw new BusinessException(RequestConstant.SAVE_RECEIVE_ADDRESS_FAILE_CODE,
                    RequestConstant.SAVE_RECEIVE_ADDRESS_FAILE_MSG + bindingResult.getFieldError().getDefaultMessage());
        }

        //业务校验
        receiveAddressService.saveReceiveAddress(receiveAddress);

        // 返回
        return ResultUtil.success();
    }

    @ApiOperation(value = "获取收件地址列表", notes = "根据用户OpenId；获取该用户的收件地址列表")
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "query", dataType = "String", name = "userOpenId", value = "用户OpenId", required = true),
        @ApiImplicitParam(paramType = "query", dataType = "Integer", name = "pageNum", value = "当前页数", required = true),
        @ApiImplicitParam(paramType = "query", dataType = "Integer", name = "pageSize", value = "每页记录数", required = true)
        })
    @RequestMapping(value = "/receiveAddressList", method = {RequestMethod.GET})
    @ResponseBody
    public Result<PageInfo<ReceiveAddress>> getUserReceiveAddressList(@RequestParam String userOpenId, @RequestParam Integer pageSize, @RequestParam Integer pageNum) {

        Page<ReceiveAddress> receiveAddresses = receiveAddressService.getUserReceiveAddressList(userOpenId, pageNum, pageSize);
        PageInfo<ReceiveAddress> receiveAddressesPageInfo = new PageInfo<>(receiveAddresses);

        return ResultUtil.success(receiveAddressesPageInfo);
    }



    @ApiOperation(value = "更新收件地址", notes = "更新收件地址")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", dataType = "ReceiveAddress", name = "receiveAddress", value = "收件地址", required = true)
    })
    @RequestMapping(value = "/receiveAddress/update", method = {RequestMethod.POST})
    @ResponseBody
    public Result updateReceiveAddress(@Valid @RequestBody ReceiveAddress receiveAddress, BindingResult bindingResult){

        // 参数校验
            if (Strings.isNullOrEmpty(receiveAddress.getUserOpenId()) || null == receiveAddress.getReceiveAddressId()) {
            throw new BusinessException(RequestConstant.UPDATA_RECEIVE_ADDRESS_PARAMETER_ERROR_CODE, RequestConstant.UPDATA_RECEIVE_ADDRESS_PARAMETER_ERROR_MSG);
        }

        if (bindingResult.hasErrors()) {
            throw new BusinessException(RequestConstant.UPDATA_RECEIVE_ADDRESS_FAILE_CODE,
                    RequestConstant.UPDATA_RECEIVE_ADDRESS_FAILE_MSG + bindingResult.getFieldError().getDefaultMessage());
        }

        // 业务处理
        receiveAddressService.updateReceiveAddress(receiveAddress);


        // 返回
        return ResultUtil.success();
    }


    @ApiOperation(value = "删除收件地址", notes = "根据用户OpenId，收件地址Id，删除该收件地址")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "userOpenId", value = "用户OpenId", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "Integer", name = "receiveAddressId", value = "收件地址Id", required = true),
    })
    @RequestMapping(value = "/receiveAddress/delete", method = {RequestMethod.GET})
    @ResponseBody
    public Result deleteReceiveAddress(@RequestParam int receiveAddressId, @RequestParam String userOpenId){

        receiveAddressService.deleteReceiveAddress(receiveAddressId, userOpenId);

        return ResultUtil.success();
    }


    @ApiOperation(value = "更新默认的收件地址", notes = "根据用户OpenId，收件地址Id，将该收件地址作为该用户默认的收件地址")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "userOpenId", value = "用户OpenId", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "Integer", name = "receiveAddressId", value = "收件地址Id", required = true),
    })
    @RequestMapping(value = "/receiveAddress/updateIsDefault", method = {RequestMethod.POST})
    @ResponseBody
    public Result updateReceiveAddressIsDefaule(@Valid @RequestBody UpdateOrderLogisticsPO updateOrderLogisticsPO, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            throw new BusinessException(RequestConstant.UPDATA_RECEIVE_ADDRESS_FAILE_CODE,
                    RequestConstant.UPDATA_RECEIVE_ADDRESS_FAILE_MSG + bindingResult.getFieldError().getDefaultMessage());
        }

        receiveAddressService.updateReceiveAddressIsDefaule(updateOrderLogisticsPO.getReceiveAddressId(),
                updateOrderLogisticsPO.getUserOpenId());

        return ResultUtil.success();
    }

}
