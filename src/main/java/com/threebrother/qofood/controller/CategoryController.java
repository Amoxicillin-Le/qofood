package com.threebrother.qofood.controller;

import com.alibaba.fastjson.JSONObject;
import com.threebrother.qofood.model.DTO.CategoryDTO;
import com.threebrother.qofood.model.Result;
import com.threebrother.qofood.service.CategoryService;
import com.threebrother.qofood.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Api(description = "分类-相关接口")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @ApiOperation(value="获取分类列表", notes="获取小程序首页的分类列表，包含该分类的商品列表")
    @RequestMapping(value = "/categorys", method = {RequestMethod.GET})
    @ResponseBody
    Result<CategoryDTO> getHomePageCategoryList(){

        JSONObject returnData = new JSONObject();

        List<CategoryDTO> categoryDTOS = categoryService.getHomePageCategoryList();

        return ResultUtil.success(categoryDTOS);
    }
}

