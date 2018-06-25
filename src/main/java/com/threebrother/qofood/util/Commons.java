package com.threebrother.qofood.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;


@Component
public final class Commons {

    /**
     * 获取随机数
     *
     * @param max
     * @param str
     * @return
     */
    public static String random(int max, String str) {
        return UUID.random(1, max) + str;
    }

    /**
     * 返回gravatar头像地址
     *
     * @param email
     * @return
     */
    public static String gravatar(String email) {
        String avatarUrl = "https://secure.gravatar.com/avatar";
        if (StringUtils.isBlank(email)) {
            return avatarUrl;
        }
        String hash = TaleUtil.MD5encode(email.trim().toLowerCase());
        return avatarUrl + "/" + hash;
    }
}
