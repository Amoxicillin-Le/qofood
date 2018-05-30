package com.threebrother.qofood.dao;

import com.threebrother.qofood.entity.Goods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodsMapper {


    List<Goods> selectListByCategoryId(Integer categoryId);
}
