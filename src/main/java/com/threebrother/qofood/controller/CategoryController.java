package com.threebrother.qofood.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.threebrother.qofood.model.DTO.CategoryDTO;
import com.threebrother.qofood.model.Result;
import com.threebrother.qofood.service.CategoryService;
import com.threebrother.qofood.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping("/categorys")
    @ResponseBody
    Result<CategoryDTO> getHomePageCategoryList(){

        JSONObject returnData = new JSONObject();

        List<CategoryDTO> categoryDTOS = categoryService.getHomePageCategoryList();

        return ResultUtil.success(categoryDTOS);

    }
}
