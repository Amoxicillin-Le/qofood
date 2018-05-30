package com.threebrother.qofood.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.threebrother.qofood.model.DTO.CategoryDTO;
import com.threebrother.qofood.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping("categorys")
    @ResponseBody
    String getHomePageCategoryList(){

        JSONObject returnData = new JSONObject();

        List<CategoryDTO> categoryDTOS = categoryService.getHomePageCategoryList();

        returnData.put("code","0000");
        returnData.put("msg","success");
        returnData.put("data",categoryDTOS);

        return JSON.toJSONString(returnData, SerializerFeature.PrettyFormat,
                SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteNullListAsEmpty);
    }
}
