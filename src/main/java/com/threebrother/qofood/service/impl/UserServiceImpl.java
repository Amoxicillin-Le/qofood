package com.threebrother.qofood.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.threebrother.qofood.common.Constant;
import com.threebrother.qofood.common.RequestConstant;
import com.threebrother.qofood.common.exception.BusinessException;
import com.threebrother.qofood.dao.UserMapper;
import com.threebrother.qofood.service.UserService;
import com.threebrother.qofood.util.WXAppletUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    @Transactional(readOnly = true)
    public String getOpenUserId(String code) {

        String  openId;

        try {
            JSONObject openIdJson = WXAppletUserInfo.getSessionKeyOropenid(code);
            if (openIdJson.containsKey(Constant.WX_FAILE_RESULT_CODE)) {
                // 返回失败
                throw new BusinessException(RequestConstant.REQUEST_WX_FAILE_CODE, RequestConstant.REQUEST_WX_FAILE_MSG);
            }
            openId = openIdJson.getString(Constant.OPENID);
        }catch (Exception e) {
            e.printStackTrace();
            // 请求异常
            throw new BusinessException(RequestConstant.REQUEST_WX_EXCEPTION_CODE, RequestConstant.REQUEST_WX_EXCEPTION_MSG);
        }

        if(Strings.isNullOrEmpty(openId)){
            throw new BusinessException(RequestConstant.REQUEST_WX_FAILE_CODE, RequestConstant.REQUEST_WX_FAILE_MSG);
        }

        // 判断是否是以经存在的用户
        if (userMapper.isExistByOpenId(openId) < Constant.INT_ONE) {
            int count = userMapper.insetUserByOpenId(openId);
            if(count < Constant.INT_ONE){
                throw new BusinessException(RequestConstant.SAVE_USER_FAILE_CODE, RequestConstant.SAVE_USER_FAILE_MSG);
            }
        }

        return openId;
    }

}
