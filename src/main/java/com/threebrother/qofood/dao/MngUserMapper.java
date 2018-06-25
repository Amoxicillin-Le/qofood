package com.threebrother.qofood.dao;

import com.threebrother.qofood.entity.MngUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MngUserMapper {

    MngUser selectMngUserByMngUserId(int mngUserId);

    MngUser login(@Param("mngUserName") String mngUserName, @Param("mngUserPassword") String mngUserPassword);

    int selectCountByMngUserName(String mngUserName);
}
