package com.threebrother.qofood.dao;

import com.github.pagehelper.Page;
import com.threebrother.qofood.entity.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 后台管理分页列表
     * @author zhaoxiaolezi
     * @date 2018/6/26 15:06
     */
    Page<Goods> selectGoodsList(@Param("goodsName") String goodsName, @Param("orderByStr") String orderByStr);

    /**
     * 删除商品
     * @author zhaoxiaolezi
     * @date 2018/6/26 16:32
     */
    void deleteGoodsByGoodsId(Integer goodsId);

    /**
     * 新增商品
     * @author zhaoxiaolezi
     * @date 2018/6/27 17:44
     */
    void saveGoods(Goods goods);

    /**
     * 根据商品名称 获取该商品名称的记录数
     * @author zhaoxiaolezi
     * @date 2018/6/27 17:58
     */
    Integer selectCountByGoodsName(@Param("goodsName")String goodsName);
}
