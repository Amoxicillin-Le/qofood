package com.threebrother.qofood.controller;

import com.alibaba.fastjson.JSONObject;
import com.threebrother.qofood.common.Constant;
import com.threebrother.qofood.model.Result;
import com.threebrother.qofood.service.UserService;
import com.threebrother.qofood.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/getOpenUserId")
    @ResponseBody
    public Result<JSONObject> getOpenUserId(@RequestParam(name = "code") String code){

        JSONObject openIdJsonObject = new JSONObject();

        String openId = userService.getOpenUserId(code);
        openIdJsonObject.put(Constant.OPEN_ID, openId);



        return ResultUtil.success(openIdJsonObject);
    }
}
