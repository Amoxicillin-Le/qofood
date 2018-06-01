package com.threebrother.qofood.service.impl;

import com.threebrother.qofood.dao.ReceivreAddressMapper;
import com.threebrother.qofood.entity.ReceiveAddress;
import com.threebrother.qofood.service.ReceiveAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceiveAddressServiceImpl implements ReceiveAddressService {

    @Autowired
    ReceivreAddressMapper receivreAddressMapper;

    @Override
    public void saveReceiveAddress(ReceiveAddress receiveAddress) {
        System.out.println("-=-=-=-=-=-=-" + receiveAddress.getReceiveAddressContactName());
        receivreAddressMapper.insertReceivreAddress(receiveAddress);
    }
}
