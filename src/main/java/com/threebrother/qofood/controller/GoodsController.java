package com.threebrother.qofood.controller;

import com.threebrother.qofood.entity.Goods;
import com.threebrother.qofood.model.Result;
import com.threebrother.qofood.service.GoodsService;
import com.threebrother.qofood.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@Api(description = "商品-相关接口")
public class GoodsController {

    @Autowired
    GoodsService goodsService;


    @ApiOperation(value = "获取商品详情", notes = "根据商品ID获取商品详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商品Id", paramType = "path", required = true, dataType = "Integer")
    })
    @RequestMapping(value = "/goods/{id}", method = {RequestMethod.GET})
    @ResponseBody
    Result<Goods> getById(@PathVariable Integer id){

        Goods goods = goodsService.getGoodsById(id);

        return ResultUtil.success(goods);
    }

}
