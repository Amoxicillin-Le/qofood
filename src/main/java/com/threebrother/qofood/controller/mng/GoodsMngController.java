package com.threebrother.qofood.controller.mng;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.threebrother.qofood.common.RequestConstant;
import com.threebrother.qofood.common.exception.BusinessException;
import com.threebrother.qofood.entity.Goods;
import com.threebrother.qofood.model.Result;
import com.threebrother.qofood.service.GoodsService;
import com.threebrother.qofood.util.ResultUtil;
import org.apache.commons.lang3.StringUtils;
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

    @RequestMapping("/delete")
    @ResponseBody
    public Result deleteGoods(HttpServletRequest request){

        String goodsId = request.getParameter("goodsId");


        // 参数校验 如果为空 或者 不是数字
        if(Strings.isNullOrEmpty(goodsId) || !StringUtils.isNumeric(goodsId)){
            throw new BusinessException(RequestConstant.DELETE_GOODS_FAILE_CODE,
                    RequestConstant.DELETE_GOODS_FAILE_MSG);
        }

        goodsService.deleteGoodsByGoodsId(Integer.valueOf(goodsId));

        return ResultUtil.success();
    }
}
