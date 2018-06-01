package com.threebrother.qofood.dao;


import com.threebrother.qofood.entity.ReceiveAddress;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReceivreAddressMapper {

    int insertReceivreAddress(ReceiveAddress receiveAddress);

    List<ReceiveAddress> selectReceiveAddressListByUserOpenId(String userOpenId);

    int selectCountByUserOpenId(String userOpenId);
}
