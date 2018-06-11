package com.threebrother.qofood.util;

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
}
