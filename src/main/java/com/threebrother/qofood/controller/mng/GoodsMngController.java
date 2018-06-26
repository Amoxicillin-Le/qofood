package com.threebrother.qofood.controller.mng;

import com.github.pagehelper.PageInfo;
import com.threebrother.qofood.entity.Goods;
import com.threebrother.qofood.service.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/mng/goods")
public class GoodsMngController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsMngController.class);

    @Autowired
    GoodsService goodsService;

    @GetMapping("")
    public String index(@RequestParam(value = "page", defaultValue = "1") int page, HttpServletRequest request){

        // PageInfo<Goods> goodsPageInfo = goodsService.selectGoodsList(page, Constant.DEFAULT_LIMIT);

        // request.setAttribute("goods", goodsPageInfo);

        return "mng/goods_list";
    }


    @RequestMapping("/pageList")
    @ResponseBody
    public PageInfo<Goods> selectGoodsList(HttpServletRequest request) {

        String pageNumber = request.getParameter("pageNumber");
        String pageSize = request.getParameter("pageSize");
        String sort = request.getParameter("sort");
        String order = request.getParameter("order");

        String goodsName = request.getParameter("goodsName");

        PageInfo<Goods> goodsPageInfo = goodsService.selectGoodsList(Integer.valueOf(pageNumber), Integer.valueOf(pageSize),
                goodsName, sort, order);

        return goodsPageInfo;
    }
}
