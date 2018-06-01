package com.threebrother.qofood.dao;


import com.threebrother.qofood.entity.ReceiveAddress;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReceivreAddressMapper {

    int insertReceivreAddress(ReceiveAddress receiveAddress);
}
