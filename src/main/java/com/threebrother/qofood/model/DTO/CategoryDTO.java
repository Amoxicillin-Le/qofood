package com.threebrother.qofood.model.DTO;

import com.threebrother.qofood.entity.Category;
import com.threebrother.qofood.entity.Goods;

import java.util.List;

public class CategoryDTO extends Category {


    List<Goods> goodsList;

    public CategoryDTO() {
    }

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }


}


