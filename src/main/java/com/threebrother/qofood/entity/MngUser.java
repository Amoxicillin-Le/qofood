package com.threebrother.qofood.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class MngUser implements Serializable {

    private Integer mngUserId;

    private String mngUserName;

    private String mngUserPassword;

    private String mngUserEmail;

    private String mngUserScreenName;

    private Timestamp createTime;

    private Timestamp activateTime;

    private Timestamp loginTime;

    public Integer getMngUserId() {
        return mngUserId;
    }

    public void setMngUserId(Integer mngUserId) {
        this.mngUserId = mngUserId;
    }

    public String getMngUserName() {
        return mngUserName;
    }

    public void setMngUserName(String mngUserName) {
        this.mngUserName = mngUserName;
    }

    public String getMngUserPassword() {
        return mngUserPassword;
    }

    public void setMngUserPassword(String mngUserPassword) {
        this.mngUserPassword = mngUserPassword;
    }

    public String getMngUserEmail() {
        return mngUserEmail;
    }

    public void setMngUserEmail(String mngUserEmail) {
        this.mngUserEmail = mngUserEmail;
    }

    public String getMngUserScreenName() {
        return mngUserScreenName;
    }

    public void setMngUserScreenName(String mngUserScreenName) {
        this.mngUserScreenName = mngUserScreenName;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getActivateTime() {
        return activateTime;
    }

    public void setActivateTime(Timestamp activateTime) {
        this.activateTime = activateTime;
    }

    public Timestamp getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Timestamp loginTime) {
        this.loginTime = loginTime;
    }
}
