package com.threebrother.qofood.controller;

import com.threebrother.qofood.entity.Goods;
import com.threebrother.qofood.model.Result;
import com.threebrother.qofood.service.GoodsService;
import com.threebrother.qofood.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    @RequestMapping("/goods/{id}")
    @ResponseBody
    Result<Goods> getById(@PathVariable Integer id){

        Goods goods = goodsService.getGoodsById(id);

        return ResultUtil.success(goods);
    }

}
