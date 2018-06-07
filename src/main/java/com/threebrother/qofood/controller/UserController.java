package com.threebrother.qofood.controller;

import com.alibaba.fastjson.JSONObject;
import com.threebrother.qofood.common.Constant;
import com.threebrother.qofood.model.Result;
import com.threebrother.qofood.service.UserService;
import com.threebrother.qofood.util.ResultUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Api(description = "用户-相关接口")
public class UserController {

    @Autowired
    UserService userService;


    @ApiOperation(value = "获取用户OpenId", notes = "根据微信登陆返回的code获取用户OpenId")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "微信登陆code", dataType = "String", paramType = "query", required = true)
    })
    @RequestMapping(value = "/getOpenUserId", method = {RequestMethod.POST})
    @ResponseBody
    public Result<JSONObject> getOpenUserId(@RequestParam(name = "code") String code){

        JSONObject openIdJsonObject = new JSONObject();

        String openId = userService.getOpenUserId(code);
        openIdJsonObject.put(Constant.OPEN_ID, openId);

        return ResultUtil.success(openIdJsonObject);
    }
}
