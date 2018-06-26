package com.threebrother.qofood.service;

import com.github.pagehelper.PageInfo;
import com.threebrother.qofood.entity.Goods;

public interface GoodsService {

    Goods getGoodsById(Integer id);

    PageInfo<Goods> selectGoodsList(int pageNum, int pageSize, String goodsName, String sort, String order);

    void deleteGoodsByGoodsId(Integer goodsId);
}
