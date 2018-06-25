package com.threebrother.qofood.service;

import com.threebrother.qofood.entity.MngUser;

public interface MngUserServier {

    MngUser queryUserById(Integer uid);

    MngUser login(String mngUserName, String mngUserPassword);
}
