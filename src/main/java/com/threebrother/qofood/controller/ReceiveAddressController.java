package com.threebrother.qofood.controller;

import com.threebrother.qofood.entity.ReceiveAddress;
import com.threebrother.qofood.model.Result;
import com.threebrother.qofood.service.ReceiveAddressService;
import com.threebrother.qofood.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
