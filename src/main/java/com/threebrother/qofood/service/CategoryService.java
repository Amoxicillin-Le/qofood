package com.threebrother.qofood.service;

import com.threebrother.qofood.entity.Category;
import com.threebrother.qofood.model.DTO.CategoryDTO;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> getHomePageCategoryList();
}
