package com.threebrother.qofood.controller;

import com.google.common.base.Strings;
import com.threebrother.qofood.common.RequestConstant;
import com.threebrother.qofood.common.exception.BusinessException;
import com.threebrother.qofood.entity.ReceiveAddress;
import com.threebrother.qofood.model.Result;
import com.threebrother.qofood.service.ReceiveAddressService;
import com.threebrother.qofood.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(description = "收件地址--相关接口")
@Controller
public class ReceiveAddressController {

    @Autowired
    ReceiveAddressService receiveAddressService;


    @ApiOperation(value = "创建收件地址", notes = "创建收件地址")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", dataType = "ReceiveAddress", name = "receiveAddress", value = "收件地址", required = true)
    })
    @RequestMapping(value = "/receiveAddress/save", method = {RequestMethod.POST})
    @ResponseBody
    public Result saveReceiveAddress(@RequestBody ReceiveAddress receiveAddress) {

        //TODO 这里需要校验参数合法性 暂时为实现
        receiveAddressService.saveReceiveAddress(receiveAddress);

        return ResultUtil.success();
    }

    @ApiOperation(value = "获取收件地址列表", notes = "根据用户OpenId；获取该用户的收件地址列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "userOpenId", value = "用户OpenId", required = true)
    })
    @RequestMapping(value = "/receiveAddressList", method = {RequestMethod.GET})
    @ResponseBody
    public Result<List<ReceiveAddress>> getUserReceiveAddressList(@RequestParam String userOpenId) {

        List<ReceiveAddress> receiveAddresses = new ArrayList<>();

        receiveAddresses = receiveAddressService.getUserReceiveAddressList(userOpenId);

        return ResultUtil.success(receiveAddresses);
    }



    @ApiOperation(value = "更新收件地址", notes = "更新收件地址")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", dataType = "ReceiveAddress", name = "receiveAddress", value = "收件地址", required = true)
    })
    @RequestMapping(value = "/receiveAddress/update", method = {RequestMethod.POST})
    @ResponseBody
    public Result updateReceiveAddress(@RequestBody ReceiveAddress receiveAddress){

        if (Strings.isNullOrEmpty(receiveAddress.getUserOpenId()) || null == receiveAddress.getReceiveAddressId()) {
            throw new BusinessException(RequestConstant.UPDATA_RECEIVE_ADDRESS_PARAMETER_ERROR_CODE, RequestConstant.UPDATA_RECEIVE_ADDRESS_PARAMETER_ERROR_MSG);
        }

        receiveAddressService.updateReceiveAddress(receiveAddress);

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
    public Result updateReceiveAddressIsDefaule(@RequestParam int receiveAddressId, @RequestParam String userOpenId){

        receiveAddressService.updateReceiveAddressIsDefaule(receiveAddressId, userOpenId);

        return ResultUtil.success();
    }
}
