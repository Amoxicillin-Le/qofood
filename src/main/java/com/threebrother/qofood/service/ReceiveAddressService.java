package com.threebrother.qofood.service;

import com.github.pagehelper.Page;
import com.threebrother.qofood.entity.ReceiveAddress;

public interface ReceiveAddressService {

    void saveReceiveAddress(ReceiveAddress receiveAddress);

    Page<ReceiveAddress> getUserReceiveAddressList(String userOpenId, int pageNum, int pageSize);

    void updateReceiveAddress(ReceiveAddress receiveAddress);

    void deleteReceiveAddress(int receiveAddressId, String userOpenId);

    void updateReceiveAddressIsDefaule(int receiveAddressId, String userOpenId);

}
