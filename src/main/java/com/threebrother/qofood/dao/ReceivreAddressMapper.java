package com.threebrother.qofood.dao;


import com.threebrother.qofood.entity.ReceiveAddress;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReceivreAddressMapper {

    int insertReceivreAddress(ReceiveAddress receiveAddress);

    List<ReceiveAddress> selectReceiveAddressListByUserOpenId(String userOpenId);

    int selectCountByUserOpenId(String userOpenId);

    ReceiveAddress selectReceiveAddressByreceiveAddressIdAndUserOpenId(@Param("receiveAddressId") Integer receiveAddressId, @Param("userOpenId") String userOpenId);

    void updateReceivreAddressByReceiveAddressIdAndUserOpenId(ReceiveAddress receiveAddress);

    void deleteReceivreAddressByReceiveAddressIdAndUserOpenId(@Param("receiveAddressId") Integer receiveAddressId, @Param("userOpenId") String userOpenId);

    ReceiveAddress selectReceiveAddressByUserOpenIdOrderByCreateTime(@Param("receiveAddressId") Integer receiveAddressId, @Param("userOpenId") String userOpenId);

    void updateIsDefaultByReceiveAddressId(@Param("receiveAddressId") Integer receiveAddressId);

    int selectDefaultReceiceAddressIdByUserOpenId(String userOpenId);

    ReceiveAddress selectDefaultReceiceAddressByUserOpenId(String userOpenId);

    List<ReceiveAddress> selectreceiveAddressListByUserOpenIdGroupByIsDefaultAndCreateTime(String userOpenId);

    void updateReceiveAddressIsDefauleByUserOpenIdAndReceiveAddressId(@Param("userOpenId")String userOpenId, @Param("receiveAddressId") int receiveAddressId);
}
