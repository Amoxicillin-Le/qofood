package com.threebrother.qofood.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.threebrother.qofood.model.Goods;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {

    @RequestMapping("/goods")
    public String test(HttpServletRequest request){

        System.out.println(request.getLocalName() + "请求");

        String imageUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527674954193&di=7a457c7d2229837768ba56fb8fc7ca80&imgtype=0&src=http%3A%2F%2Fimg.alicdn.com%2Fimgextra%2Fi2%2F924616935%2FTB2SIWziwnH8KJjSspcXXb3QFXa_%2521%2521924616935-0-beehive-scenes.jpg";

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("code", "0000");
        jsonObject.put("msg", "请求成功");

        List<Goods> goodsList = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            Goods goods = new Goods();
            goods.setName("测试商品" + i);
            goods.setPrice(Integer.toString(i));
            goods.setDescription("啦" + i + "啦" + i);
            goods.setSellCount("100" + i);
            goods.setCount("0");
            goods.setRating("100");
            goods.setInfo("info什么info 就是好吃");
            goods.setIcon(imageUrl);
            goods.setImageUrl(imageUrl);

            goodsList.add(goods);
        }

        jsonObject.put("data", goodsList);

        return JSON.toJSONString(jsonObject, SerializerFeature.PrettyFormat,
                SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteNullListAsEmpty);
    }

}


