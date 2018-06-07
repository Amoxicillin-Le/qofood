package com.threebrother.qofood.service.impl;

import com.threebrother.qofood.common.Constant;
import com.threebrother.qofood.common.RequestConstant;
import com.threebrother.qofood.common.exception.BusinessException;
import com.threebrother.qofood.dao.ReceivreAddressMapper;
import com.threebrother.qofood.entity.ReceiveAddress;
import com.threebrother.qofood.service.ReceiveAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public void updateReceiveAddress(ReceiveAddress receiveAddress) {
        ReceiveAddress oldReceiveAddress = receivreAddressMapper.selectReceiveAddressByreceiveAddressIdAndUserOpenId(receiveAddress.getReceiveAddressId(), receiveAddress.getUserOpenId());

        if (null == oldReceiveAddress) {
            throw new BusinessException(RequestConstant.UPDATA_RECEIVE_ADDRESS_FAILE_CODE, RequestConstant.UPDATA_RECEIVE_ADDRESS_FAILE_MSG);
        }

        receivreAddressMapper.updateReceivreAddressByReceiveAddressIdAndUserOpenId(receiveAddress);
    }

    @Override
    @Transactional
    public void deleteReceiveAddress(int receiveAddressId, String userOpenId) {


        List<ReceiveAddress> receiveAddresses = receivreAddressMapper.selectReceiveAddressListByUserOpenId(userOpenId);
        if(receiveAddresses.isEmpty() || receivreAddressMapper.selectReceiveAddressListByUserOpenId(userOpenId).size() <= Constant.INT_ONE){
            throw new BusinessException(RequestConstant.DELETE_RECEIVE_ADDRESS_FAILE_CODE, RequestConstant.DELETE_RECEIVE_ADDRESS_FAILE_MSG);
        }

        ReceiveAddress receiveAddress = receivreAddressMapper.selectReceiveAddressByreceiveAddressIdAndUserOpenId(receiveAddressId, userOpenId);

        if (null == receiveAddress) {
            throw new BusinessException(RequestConstant.DELETE_RECEIVE_ADDRESS_FAILE_CODE, RequestConstant.DELETE_RECEIVE_ADDRESS_FAILE_MSG);
        }

        receivreAddressMapper.deleteReceivreAddressByReceiveAddressIdAndUserOpenId(receiveAddressId, userOpenId);


        if (receiveAddress.getDefault()) {
            receiveAddress = receivreAddressMapper.selectReceiveAddressByUserOpenIdOrderByCreateTime(receiveAddress.getReceiveAddressId() ,userOpenId);
            System.out.println("receiveAddress is null ? ===> " + (null == receiveAddress));
            if(null != receiveAddress) {
                receivreAddressMapper.updateIsDefaultByReceiveAddressId(receiveAddress.getReceiveAddressId());
            }else{
                throw new BusinessException(RequestConstant.DELETE_RECEIVE_ADDRESS_FAILE_CODE, RequestConstant.DELETE_RECEIVE_ADDRESS_FAILE_MSG);
            }
        }

    }

    @Override
    public void updateReceiveAddressIsDefaule(int receiveAddressId, String userOpenId) {

        ReceiveAddress receiveAddress = receivreAddressMapper.selectReceiveAddressByreceiveAddressIdAndUserOpenId(receiveAddressId, userOpenId);
        if (null == receiveAddress) {
            throw new BusinessException(RequestConstant.RECEIVE_ADDRESS_NON_EXISTENT_CODE, RequestConstant.RECEIVE_ADDRESS_NON_EXISTENT_MSG);
        }

        receivreAddressMapper.updateReceiveAddressIsDefauleByUserOpenIdAndReceiveAddressId(userOpenId, receiveAddressId);
    }


}
