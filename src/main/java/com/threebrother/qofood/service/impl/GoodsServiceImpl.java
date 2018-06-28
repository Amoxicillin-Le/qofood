package com.threebrother.qofood.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.threebrother.qofood.common.Constant;
import com.threebrother.qofood.common.RequestConstant;
import com.threebrother.qofood.common.exception.BusinessException;
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
    public PageInfo<Goods> selectGoodsList(int pageNum, int pageSize, String goodsName, String isDelete, String sort, String order) {

        // 处理排序字段 默认是创建时间排倒序
        String orderByStr = "";
        if(!Strings.isNullOrEmpty(sort)) {
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

        // 处理是否删除字段
        Boolean isDeleteB = null;
        if(!Strings.isNullOrEmpty(isDelete)) {
            if(Integer.valueOf(isDelete) == Constant.INT_ONE) {
                isDeleteB = true;
            }
            if(Integer.valueOf(isDelete) == Constant.INT_ZERO){
                isDeleteB = false;
            }
        }

        // 分页查询
        PageHelper.startPage(pageNum, pageSize);
        Page<Goods> goodsPage = goodsMapper.selectGoodsList(goodsName, isDeleteB, orderByStr);

        return new PageInfo<>(goodsPage);
    }

    @Override
    @Transactional
    public void deleteGoodsByGoodsId(Integer goodsId) {

        // 删除分类关联数据
        goodsMapper.deleteCateGoryGoodsRelByGoodsId(goodsId);
        // 删除商品
        goodsMapper.deleteGoodsByGoodsId(goodsId);
    }

    @Override
    @Transactional
    public void saveGoods(Goods goods) {

        Integer count = goodsMapper.selectCountByGoodsName(goods.getGoodsName());
        if(count > Constant.INT_ZERO){
            throw new BusinessException(RequestConstant.SAVE_GOODS_FAILE_EXISTS_CODE,
                    RequestConstant.SAVE_GOODS_FAILE_EXISTS_MSG);
        }
        goodsMapper.saveGoods(goods);
    }

    @Override
    @Transactional
    public void updateGoods(Goods goods) {

        Integer count = goodsMapper.selectCountByGoodsId(goods.getGoodsId());
        if(count <= Constant.INT_ZERO){
            throw new BusinessException(RequestConstant.UPDATE_GOODS_FAILE_NON_EXISTS_CODE,
                    RequestConstant.UPDATE_GOODS_FAILE_NON_EXISTS_MSG);
        }

        goodsMapper.updateGoods(goods);
    }

}
