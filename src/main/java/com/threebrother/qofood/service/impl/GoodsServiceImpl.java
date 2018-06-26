package com.threebrother.qofood.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.threebrother.qofood.dao.GoodsMapper;
import com.threebrother.qofood.entity.Goods;
import com.threebrother.qofood.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    GoodsMapper goodsMapper;

    @Override
    @Transactional(readOnly = true)
    public Goods getGoodsById(Integer id) {

        Goods goods = new Goods();
        goods = goodsMapper.selectByGoodsId(id);

        return  goods;
    }

    @Override
    @Transactional(readOnly = true)
    public PageInfo<Goods> selectGoodsList(int pageNum, int pageSize, String goodsName, String sort, String order) {


        String orderByStr = "";
        if(!Strings.isNullOrEmpty(sort)){
            if(sort.equals("createTime")) {
                orderByStr = "create_time " + order.toUpperCase();
            }
            if(sort.equals("goodsSalesVolume")) {
                orderByStr = "goods_sales_volume " + order.toUpperCase();
            }
            if(sort.equals("goodsPrice")) {
                orderByStr = "goods_price " + order.toUpperCase();
            }
        }else{
            orderByStr = "create_time DESC";
        }

        PageHelper.startPage(pageNum, pageSize);
        Page<Goods> goodsPage = goodsMapper.selectGoodsList(goodsName, orderByStr);

        return new PageInfo<>(goodsPage);
    }

}
