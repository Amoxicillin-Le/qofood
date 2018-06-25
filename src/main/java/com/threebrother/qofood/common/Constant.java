package com.threebrother.qofood.common;

import java.math.BigDecimal;

public class Constant {

    public static int INT_ZERO = 0;

    public static int INT_ONE = 1;

    public static int INT_TWO = 2;

    public static BigDecimal DEFAULT_FREIGHT_AMOUNT = new BigDecimal(5);

    public static BigDecimal BIGDECIMAL_ZERO = new BigDecimal(0);

    public static String WX_FAILE_RESULT_CODE = "errcode";

    public static String OPENID = "openid"; // 微信返回的是该字段

    public static String OPEN_ID = "openId";

    public static String ORDER_ID = "orderId";

    public static String[] SPECIAL_AREA = {"新疆", "西藏", "海南"};


    public static String LOGIN_SESSION_KEY = "login_user";

    public static String USER_IN_COOKIE = "S_L_ID";

    /**
     * aes加密加盐
     */
    public static String AES_SALT = "0123456789abcdef";


}
