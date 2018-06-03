package com.threebrother.qofood.controller;

import com.google.common.base.Strings;
import com.threebrother.qofood.common.RequestConstant;
import com.threebrother.qofood.common.exception.BusinessException;
import com.threebrother.qofood.entity.ReceiveAddress;
import com.threebrother.qofood.model.Result;
import com.threebrother.qofood.service.ReceiveAddressService;
import com.threebrother.qofood.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ReceiveAddressController {

    @Autowired
    ReceiveAddressService receiveAddressService;

    @RequestMapping(value = "/receiveAddress/save")
    @ResponseBody
    public Result saveReceiveAddress(@RequestBody ReceiveAddress receiveAddress) {

        //TODO 这里需要校验参数合法性 暂时为实现
        receiveAddressService.saveReceiveAddress(receiveAddress);

        return ResultUtil.success();
    }


    @RequestMapping(value = "/receiveAddressList")
    @ResponseBody
    public Result<List<ReceiveAddress>> getUserReceiveAddressList(@RequestParam String userOpenId) {

        List<ReceiveAddress> receiveAddresses = new ArrayList<>();

        receiveAddresses = receiveAddressService.getUserReceiveAddressList(userOpenId);

        return ResultUtil.success(receiveAddresses);
    }


    @RequestMapping(value = "/receiveAddress/update")
    @ResponseBody
    public Result updateReceiveAddress(@RequestBody ReceiveAddress receiveAddress){

        if (Strings.isNullOrEmpty(receiveAddress.getUserOpenId()) || null == receiveAddress.getReceiveAddressId()) {
            throw new BusinessException(RequestConstant.UPDATA_RECEIVE_ADDRESS_PARAMETER_ERROR_CODE, RequestConstant.UPDATA_RECEIVE_ADDRESS_PARAMETER_ERROR_MSG);
        }

        receiveAddressService.updateReceiveAddress(receiveAddress);

        return ResultUtil.success();
    }

    @RequestMapping(value = "/receiveAddress/delete")
    @ResponseBody
    public Result deleteReceiveAddress(@RequestParam int receiveAddressId, @RequestParam String userOpenId){

        receiveAddressService.deleteReceiveAddress(receiveAddressId, userOpenId);

        return ResultUtil.success();
    }
}
