package com.threebrother.qofood.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    int isExistByOpenId(String openId);

    int insetUserByOpenId(String openId);
}
