package com.threebrother.qofood.service.impl;

import com.threebrother.qofood.common.Constant;
import com.threebrother.qofood.dao.ReceivreAddressMapper;
import com.threebrother.qofood.entity.ReceiveAddress;
import com.threebrother.qofood.service.ReceiveAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceiveAddressServiceImpl implements ReceiveAddressService {

    @Autowired
    ReceivreAddressMapper receivreAddressMapper;

    @Override
    public void saveReceiveAddress(ReceiveAddress receiveAddress) {

        //TODO 获取该用户是否存在收件地址 目前首次录入的收件地址为默认的收件地址
        int count = receivreAddressMapper.selectCountByUserOpenId(receiveAddress.getUserOpenId());
        if(count < Constant.INT_ONE){
            receiveAddress.setDefault(true);
        }else{
            receiveAddress.setDefault(false);
        }

        receivreAddressMapper.insertReceivreAddress(receiveAddress);
    }

    @Override
    public List<ReceiveAddress> getUserReceiveAddressList(String userOpenId) {
        return receivreAddressMapper.selectReceiveAddressListByUserOpenId(userOpenId);
    }


}
