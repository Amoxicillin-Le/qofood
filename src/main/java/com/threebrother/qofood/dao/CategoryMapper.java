package com.threebrother.qofood.dao;

import com.threebrother.qofood.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {

    List<Category> selectListByIsShow(Boolean isShow);
}
