package com.threebrother.qofood.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PreferentialStrategyMapper {

    Integer getPreferentialStrategyTypeById();
}
