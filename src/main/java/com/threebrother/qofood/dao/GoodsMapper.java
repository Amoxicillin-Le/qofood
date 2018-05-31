package com.threebrother.qofood.dao;

import com.threebrother.qofood.entity.Goods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodsMapper {


    /**
     * 获取种类下的商品列表
     * @author zhaoxiaolezi
     * @date 2018/5/31 16:20
     */
    List<Goods> selectListByCategoryId(Integer categoryId);

    /**
     * 根据商品Id获取商品对象
     * @author zhaoxiaolezi
     * @date 2018/5/31 16:21
     */
    Goods selectByGoodsId(Integer goodsId);
}
