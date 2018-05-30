package com.threebrother.qofood.service.impl;

import com.threebrother.qofood.dao.CategoryMapper;
import com.threebrother.qofood.dao.GoodsMapper;
import com.threebrother.qofood.entity.Category;
import com.threebrother.qofood.entity.Goods;
import com.threebrother.qofood.model.DTO.CategoryDTO;
import com.threebrother.qofood.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public List<CategoryDTO> getHomePageCategoryList() {

        List<CategoryDTO> categoryDTOS = new ArrayList<>();

        List<Category> categories = new ArrayList<>();
        categories = categoryMapper.selectListByIsShow(true);

        if(categories.isEmpty()){
            return categoryDTOS;
        }

        for(Category category : categories){
            List<Goods> goods = goodsMapper.selectListByCategoryId(category.getCategoryId());

            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setCategoryId(category.getCategoryId());
            categoryDTO.setCategoryBackgroundUrl(category.getCategoryBackgroundUrl());
            categoryDTO.setCategoryName(category.getCategoryName());
            categoryDTO.setGoodsList(goods);

            categoryDTOS.add(categoryDTO);
        }

        return categoryDTOS;
    }
}
