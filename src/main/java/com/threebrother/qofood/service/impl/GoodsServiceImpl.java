package com.threebrother.qofood.service.impl;

import com.threebrother.qofood.dao.GoodsMapper;
import com.threebrother.qofood.entity.Goods;
import com.threebrother.qofood.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public Goods getGoodsById(Integer id) {

        Goods goods = new Goods();
        goods = goodsMapper.selectByGoodsId(id);

        return  goods;
    }

}
