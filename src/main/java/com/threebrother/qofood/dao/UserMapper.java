package com.threebrother.qofood.dao;

import com.threebrother.qofood.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    public User selectByPrimaryKey(Integer id);
}
