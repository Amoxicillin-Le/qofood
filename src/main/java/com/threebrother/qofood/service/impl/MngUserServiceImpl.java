package com.threebrother.qofood.service.impl;

import com.threebrother.qofood.common.MngRequestConstant;
import com.threebrother.qofood.common.exception.BusinessException;
import com.threebrother.qofood.dao.MngUserMapper;
import com.threebrother.qofood.entity.MngUser;
import com.threebrother.qofood.service.MngUserServier;
import com.threebrother.qofood.util.TaleUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MngUserServiceImpl implements MngUserServier {

    @Autowired
    private MngUserMapper mngUserMapper;

    @Override
    @Transactional(readOnly = true)
    public MngUser queryUserById(Integer mngUserId) {
        MngUser mngUser = null;

        if (null != mngUserId) {
            mngUser = mngUserMapper.selectMngUserByMngUserId(mngUserId);
        }
        return mngUser;
    }


    @Override
    @Transactional
    public MngUser login(String mngUserName, String mngUserPassword) {

        if (StringUtils.isBlank(mngUserName) || StringUtils.isBlank(mngUserPassword)) {
            throw new BusinessException(MngRequestConstant.USERNAME_AND_USERPASSWORD_CAN_NOT_EMPTY_CODE,
                    MngRequestConstant.USERNAME_AND_USERPASSWORD_CAN_NOT_EMPTY_MSG);
        }

        int count = mngUserMapper.selectCountByMngUserName(mngUserName);
        if (count < 1) {
            throw new BusinessException(MngRequestConstant.NON_EXISTS_USERNAME_CODE,
                    MngRequestConstant.NON_EXISTS_USERNAME_MSG);
        }

        String pwdMd5 = TaleUtil.MD5encode(mngUserName + mngUserPassword);

        MngUser mngUser = mngUserMapper.login(mngUserName, pwdMd5);
        if (null == mngUser) {
            throw new BusinessException(MngRequestConstant.USERNAME_OR_USERPASSWORD_ERROR_CODE,
                    MngRequestConstant.USERNAME_OR_USERPASSWORD_ERROR_MSG);
        }

        return mngUser;
    }


}
